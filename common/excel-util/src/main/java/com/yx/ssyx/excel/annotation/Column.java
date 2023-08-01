package com.yx.ssyx.excel.annotation;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Column {

    String name() default "";

    boolean primaryKey() default false;

    int keyOrder() default 0;

}
