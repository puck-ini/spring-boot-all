package org.zchzh.validator.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * @author zengchzh
 * @date 2021/8/11
 */
@Slf4j
@RestControllerAdvice(basePackages = "org.zchzh.validator.controller")
public class ValidatorExceptionHandler {

    /**
     * 处理不被 @RequestBody 标识的类参数
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    public String bindExceptionHandler(BindException e) {
        log.error("ValidatorExceptionHandler bindExceptionHandler : ", e);
        return Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();
    }

    /**
     * 获取 Validator 校验异常 转换成自定义异常，处理被 @RequestBody 标识的类参数
     * @param e MethodArgumentNotValidException 异常
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.error("ValidatorExceptionHandler methodArgumentNotValidExceptionHandler : ", e);
        return Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();
    }

}
