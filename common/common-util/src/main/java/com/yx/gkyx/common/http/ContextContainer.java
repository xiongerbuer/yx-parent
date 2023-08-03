package com.yx.gkyx.common.http;

import org.springframework.util.Assert;

public class ContextContainer {

    public static final String MODEL_ATTR_USER_CONTEXT = "userContext";

    // TODO: 线程间传递，线程池内线程间传递
    public static final ThreadLocal<UserContext> contextContainer = new ThreadLocal<>();

    public static UserContext getContext() {
        return contextContainer.get();
    }

    public static void setContext(UserContext context) {
        contextContainer.set(context);
    }

    public static void clearContext() {
        contextContainer.remove();
    }

    public static UserContext checkLogin() {
        UserContext context = contextContainer.get();
        Assert.notNull(context, "未检查到登录信息，请重新登录");
        Long tenantId = context.getCurrentTenantId();
        Assert.notNull(tenantId, "未检查到对应租户信息，未授权租户");
        return context;
    }
}
