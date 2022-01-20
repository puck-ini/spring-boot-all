package org.zchzh.condition.condition;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.zchzh.condition.annotation.TestMyCondition;

import java.util.Map;
import java.util.Objects;

/**
 * @author zengchzh
 * @date 2021/10/8
 */

@Slf4j
public class TestCondition implements Condition {

    /**
     *
     * @param context 可以获取配置文件中的配置
     * @param metadata 可以获取自定义的注解上的方法值
     * @return
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String typeStr = context.getEnvironment().getProperty("condition.type");
        log.info("typeStr : " + typeStr);
        Map<String, Object> map = metadata.getAnnotationAttributes(TestMyCondition.class.getName());
        if (Objects.nonNull(map)) {
            String value =  map.get("value") == null ? "" : (String) map.get("value");
            log.info("value : " + value);
        }
        return true;
    }
}
