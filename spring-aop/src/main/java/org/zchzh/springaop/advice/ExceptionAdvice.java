package org.zchzh.springaop.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public Object handle(Exception e) {
        log.info(">>>>>>>> log exception - {}", new Date());
        // 通过这种方式在发生异常时将 http 请求的状态修改成500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("advice - " + e.getMessage());
    }
}
