package org.zchzh.filter.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zchzh.filter.filter.LogFilter1;

/**
 * @author zengchzh
 * @date 2021/10/13
 * 方式二：通过配置 bean
 */
@Configuration
public class FilterConfig {


    @Bean
    public FilterRegistrationBean<LogFilter1> filterRegistrationBean() {
        FilterRegistrationBean<LogFilter1> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LogFilter1());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("log1");
        return registrationBean;
    }
}
