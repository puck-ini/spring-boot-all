package org.zchzh.springaop.aspect;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.http.useragent.UserAgentUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.zchzh.springaop.annotation.InvokeRecord;


import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author zengchzh
 * @date 2021/9/10
 */
@Slf4j
@Component
public class InvokeRecordHandler extends BaseMethodAdviceHandler<Object> {

    @Autowired
    private ApplicationContext context;

    @Override
    public void onComplete(ProceedingJoinPoint point, long startTime, boolean permitted, Object result) {
        String methodDesc = getMethodDesc(point);
        Object[] args = point.getArgs();
        long costTime = System.currentTimeMillis() - startTime;

        log.warn("\n{} 执行结束，耗时={}ms，入参={}, 出参={}",
                methodDesc, costTime,
               JSONUtil.toJsonStr(args),
                JSONUtil.toJsonStr(result));
    }

    @Override
    protected String getMethodDesc(ProceedingJoinPoint point) {
        Method targetMethod = getTargetMethod(point);
        // 获得方法上的 InvokeRecordAnno
        InvokeRecord anno = targetMethod.getAnnotation(InvokeRecord.class);
        String description = "";
        // 如果没有指定注解方法说明，那么使用默认的方法说明
        if (Objects.isNull(anno) || StringUtils.isEmpty(anno.value())) {
            description = super.getMethodDesc(point);
        } else {
            description = anno.value();
        }
        return description;
    }




    /**
     * 获取操作信息
     * @param joinPoint
     * @return
     */
    public static String getMethodInfo(ProceedingJoinPoint joinPoint){
        // 获取连接点目标类名
        String targetName = joinPoint.getTarget().getClass().getName();
        // 获取连接点签名的方法名
        String methodName = joinPoint.getSignature().getName();
        //获取连接点参数
        Object[] args = joinPoint.getArgs();
        //根据连接点类的名字获取指定类
        Class targetClass = null;
        try {
            targetClass = Class.forName(targetName);
        }catch (Exception e){
            log.error("反射获取类失败：{}", e.getMessage());
            return null;
        }
        //获取类里面的方法
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == args.length) {
                    if (Objects.nonNull(method.getAnnotation(InvokeRecord.class))) {
                        description = method.getAnnotation(InvokeRecord.class).value();
                    }
                    break;
                }
            }
        }
        return description;
    }

    /**
     *  获取方法参数名和参数值
     * @param joinPoint
     * @return
     */
    private String getNameAndValue(ProceedingJoinPoint joinPoint) {

        final Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        final String[] names = methodSignature.getParameterNames();
        final Object[] args = joinPoint.getArgs();

        if (ArrayUtil.isEmpty(names) || ArrayUtil.isEmpty(args)) {
            return "";
        }
        if (names.length != args.length) {
            log.warn("{}方法参数名和参数值数量不一致", methodSignature.getName());
            return "";
        }
        Map<String, Object> map = new HashMap<>(16);
        for (int i = 0; i < names.length; i++) {
            map.put(names[i], args[i]);
        }
        String jsonResult = "unknown args";
        try {
            jsonResult = JSONUtil.toJsonStr(map);
        } catch (Exception e) {
            log.error("部分参数无法序列化为json", e);
        }
        return jsonResult;
    }


    private static final String LOCALHOST = "127.0.0.1";

    private static final String COMMA = ",";

    private static final String UNKNOWN = "unknown";

    private static final String X_FORWARDED_FOR = "x-forwarded-for";

    private static final String PROXY_CLIENT_IP = "Proxy-Client-IP";

    private static final String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";

    /**
     * 获取ip地址
     * @param request http request
     * @return 返回id地址
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader(X_FORWARDED_FOR);
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(PROXY_CLIENT_IP);
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(WL_PROXY_CLIENT_IP);
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.contains(COMMA)) {
            ip = ip.split(",")[0];
        }
        if (LOCALHOST.equals(ip)) {
            // 获取本机真正的ip地址
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                log.error(e.getMessage(), e);
            }
        }
        return ip;
    }

}
