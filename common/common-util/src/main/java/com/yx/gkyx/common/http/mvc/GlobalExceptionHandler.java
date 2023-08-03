package com.yx.gkyx.common.http.mvc;

import com.yx.gkyx.common.exception.CodeBasedException;
import com.yx.gkyx.common.http.ContextContainer;
import com.yx.gkyx.common.vo.CommonResult;
import com.fasterxml.jackson.core.JacksonException;
import com.google.common.base.Throwables;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CodeBasedException.class)
    public CommonResult<?> baseException(CodeBasedException e) {
        log(e);
        return CommonResult.failed(e.getErrorMessage(), e.getMessage());
    }

    @ExceptionHandler(ResponseStatusException.class)
    public CommonResult<?> exceptionHandler(ResponseStatusException e) {
        log(e);
        return CommonResult.failed(e.getReason());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public CommonResult<?> exceptionHandler(ValidationException e) {
        log(e);
        return CommonResult.failed(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public CommonResult<?> exceptionHandler(BindException e) {
        log(e);
        List<ObjectError> allErrors = e.getAllErrors();
        ObjectError objectError = allErrors.get(0);
        return CommonResult.failed(objectError.getDefaultMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(JacksonException.class)
    public CommonResult<?> exceptionHandler(JacksonException e) {
        log(e);
        return CommonResult.failed("json数据不合法:" + e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public CommonResult<?> exceptionHandler(Exception e) {
        log(e);
        List<Throwable> causalChain = Throwables.getCausalChain(e);
        for (Throwable t : causalChain) {
            if (t instanceof ValidationException) {
                return exceptionHandler((ValidationException) t);
            } else if (t instanceof BindException) {
                return exceptionHandler((BindException) e);
            }
        }
        return CommonResult.failed(e.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    public CommonResult<List<String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        List<String> errors = new ArrayList<>();
        for (ObjectError error : e.getBindingResult().getAllErrors()) {
            errors.add(error.getDefaultMessage());
        }
        return CommonResult.failed(String.join(",", errors));
    }

    private static void log(Throwable e) {
        log.error("request error: " + ContextContainer.getContext(), e);
    }

    @Configuration
    @ConditionalOnClass(ResourceNotFoundException.class)
    static class SpringDataRestExceptionHandler {

        @ResponseStatus(HttpStatus.NOT_FOUND)
        @ExceptionHandler(ResourceNotFoundException.class)
        public CommonResult<?> exceptionHandler(ResourceNotFoundException e) {
            log(e);
            return CommonResult.failed("查询数据不存在");
        }

    }
}