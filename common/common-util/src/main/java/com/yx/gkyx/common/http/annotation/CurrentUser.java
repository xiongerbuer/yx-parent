package com.yx.gkyx.common.http.annotation;


import com.yx.gkyx.common.http.UserContext;
import com.yx.gkyx.common.http.mvc.CurrentUserHandlerMethodArgumentResolver;
import springfox.documentation.annotations.ApiIgnore;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @see CurrentUserHandlerMethodArgumentResolver
 * @see UserContext
 */
@ApiIgnore
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrentUser {
}
