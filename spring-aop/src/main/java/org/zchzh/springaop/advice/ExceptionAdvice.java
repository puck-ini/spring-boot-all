package org.zchzh.springaop.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

/**
 * @author zengchzh
 * @date 2021/6/1
 */
@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(Exception.class)
    public String handle(Exception e) {
        log.info(">>>>>>>> log exception - {}", new Date());
        return "advice - " + e.getMessage();
    }
}
