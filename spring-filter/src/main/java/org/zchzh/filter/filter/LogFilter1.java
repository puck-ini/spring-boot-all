package org.zchzh.filter.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author zengchzh
 * @date 2021/10/13
 *
 * 方式二：通过配置 bean
 */
@Slf4j
public class LogFilter1 implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("log1 : " + LocalDateTime.now());
        chain.doFilter(request, response);
    }
}
