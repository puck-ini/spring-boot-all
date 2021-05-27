package org.zchzh.quartz.util;


import org.zchzh.quartz.job.base.BaseJob;

/**
 * @author zengchzh
 * @date 2021/3/4
 */
public class JobUtil {

    private static final String JOB_PACKAGE_NAME = "com.zchzh.quartztest.job.";

    /**
     * 根据全类名获取Job实例
     *
     * @param classname Job全类名
     * @return {@link BaseJob} 实例
     * @throws Exception 泛型获取异常
     */
    public static BaseJob getClass(String classname) throws Exception {
        Class<?> clazz = Class.forName(classname);
        return (BaseJob) clazz.newInstance();
    }


//    public static void main(String[] args) throws ClassNotFoundException {
//        String classname = JOB_PACKAGE_NAME + "HelloJob";
//        Class<?> clazz = Class.forName(classname);
//        System.out.println("args = " + clazz.getName());
//    }
}
