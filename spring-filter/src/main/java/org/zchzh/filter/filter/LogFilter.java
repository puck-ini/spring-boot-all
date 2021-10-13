package org.zchzh.filter.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author zengchzh
 * @date 2021/10/13
 *
 * 方式一：使用 @WebFilter 注解，启动类添加 @ServletComponentScan 注解
 */

@Slf4j
@WebFilter(urlPatterns = "/*", filterName = "log")
public class LogFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // HttpServletRequest 和 HttpServletResponse 以流的形式读取只能读一次，需要通过包装类复制一份，例如 ContentCachingRequestWrapper 或者 ContentCachingResponseWrapper
        HttpServletRequest request1 = (HttpServletRequest) request;
        HttpServletResponse response1 = (HttpServletResponse) response;
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request1);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response1);
        log.info("log : " + LocalDateTime.now());
        chain.doFilter(request, response);
    }
}
