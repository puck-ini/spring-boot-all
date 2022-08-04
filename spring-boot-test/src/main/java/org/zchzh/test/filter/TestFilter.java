package org.zchzh.test.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author zengchzh
 * @date 2022/8/3
 */

@Slf4j
@WebFilter(urlPatterns = "/user/login", filterName = "log")
public class TestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("request time : " + LocalDateTime.now());
        chain.doFilter(request, response);
    }
}
