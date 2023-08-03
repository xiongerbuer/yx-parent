package com.yx.gkyx.common.exception;

import com.yx.gkyx.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//AOP 面向切面
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class) //异常处理器
    @ResponseBody  //返回json数据
    public Result<String> error(Exception e) {
        e.printStackTrace();
        log.info("异常信息:{}", e.getMessage());
        return Result.fail(null);
    }

    //自定义异常处理
    @ExceptionHandler(SsyxException.class)
    @ResponseBody
    public Result<Object> error(SsyxException exception) {
        return Result.build(null, exception.getCode(), exception.getMessage());
    }
}
