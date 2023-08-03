package com.yx.gkyx.common.http;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.AllArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.util.Base64Utils;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

@AllArgsConstructor
public class FeignContextInterceptor implements RequestInterceptor, Ordered {

    @Override
    public void apply(RequestTemplate template) {
        UserContext context = ContextContainer.getContext();
        if (context == null) {
            return;
        }
        template.header("currentUserName", Base64Utils.encodeToString(context.getCurrentUserName().getBytes(StandardCharsets.UTF_8)));
        template.header("currentUserId", Objects.toString(context.getCurrentUserId()));
        template.header("currentTenantId", Objects.toString(context.getCurrentTenantId()));
        template.header("userType", Objects.toString(context.getUserType()));
        template.header("loginType", Objects.toString(context.getLoginType()));
        template.header("account", Objects.toString(context.getAccount()));
        template.header("phone", Objects.toString(context.getPhone()));
    }

    @Override
    public int getOrder() {
        return 1;
    }
}