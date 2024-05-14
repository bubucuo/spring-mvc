package org.example.springmvc.handler;

import org.example.springmvc.model.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *  拦截所有 请求api过程中抛出的异常
 *  统一返回json数据
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Object err(Exception e) {
        log.error("error", e);
        return R.err(e.getMessage());
    }
}
