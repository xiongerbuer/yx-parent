package com.yx.ssyx.common.http.mvc;

import com.yx.ssyx.common.http.annotation.IgnoreResponseAdvice;
import com.yx.ssyx.common.vo.CommonResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 实现ResponseBodyAdvice接口，可以对返回值在输出之前进行修改
 */
@RestControllerAdvice(basePackages = {"com.yx", "com.ssyx"})
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {

    @Autowired
    ObjectMapper objectMapper;

    //判断支持的类型
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        // 检查注解是否存在，存在则忽略拦截
        if (methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }
        if (methodParameter.getMethod().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }
        if (methodParameter.getDeclaringClass().getName().contains("springfox")) {
            return false;
        }
        return true;
    }

    @Override
    @SneakyThrows
    public Object beforeBodyWrite(Object o,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> converterClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        // 判断为null构建ResponseData对象进行返回
        if (o == null) {
            return CommonResult.success(null);
        }

        // 判断是ResponseData子类或其本身就返回Object o本身，因为有可能是接口返回时创建了ResponseData,这里避免再次封装
        if (o instanceof CommonResult) {
            return o;
        }
        // String特殊处理，否则会抛异常
        if (o instanceof String) {
            return objectMapper.writeValueAsString(CommonResult.success(o));
        }
        return CommonResult.success(o);
    }
}
