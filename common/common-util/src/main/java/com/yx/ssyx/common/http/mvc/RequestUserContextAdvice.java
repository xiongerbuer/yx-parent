package com.yx.ssyx.common.http.mvc;

import com.yx.ssyx.common.constant.DateTimeConstants;
import com.yx.ssyx.common.http.ContextContainer;
import com.yx.ssyx.common.http.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.lang.Nullable;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyEditorSupport;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.yx.ssyx.common.http.ContextContainer.MODEL_ATTR_USER_CONTEXT;


/**
 * @see ContextContainer
 */
@Slf4j
@ControllerAdvice
public class RequestUserContextAdvice implements HandlerInterceptor {

    @Autowired
    RedisOperations<String, String> redisOperations;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(LocalDateTime.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalDateTime.parse(text, DateTimeConstants.DATE_TIME));
            }
        });
        webDataBinder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalDate.parse(text, DateTimeConstants.DATE));
            }
        });
    }

    @ModelAttribute(MODEL_ATTR_USER_CONTEXT)
    protected UserContext setContext(@RequestHeader(name = "userType", required = false) String userType,
                                     @RequestHeader(name = "loginType", required = false) String loginType,
                                     @RequestHeader(name = "currentTenantId", required = false, defaultValue = "-1") Long tenantId,
                                     @RequestHeader(name = "currentUserId", required = false, defaultValue = "-1") Long userId,
                                     @RequestHeader(name = "currentUserName", required = false) String userName,
                                     @RequestHeader(name = "account", required = false) String account,
                                     @RequestHeader(name = "phone", required = false) String phone) {
        if (tenantId == -1L && userId == -1 && StringUtils.isEmpty(userName)) {
            return null;
        }
        UserContext context = new UserContext();
        context.setUserType(userType);
        context.setLoginType(loginType);
        context.setCurrentTenantId(tenantId);
        try {
            byte[] bytes = Base64Utils.decodeFromString(userName);
            //3.2 字节数组解码
            context.setCurrentUserName(new String(bytes, StandardCharsets.UTF_8));
        } catch (Exception e) {
            context.setCurrentUserName(userName);
        }
        context.setCurrentUserId(userId);
        context.setAccount(account);
        context.setPhone(phone);
        ContextContainer.setContext(context);
        return context;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                @Nullable Exception ex) throws Exception {
        try {
            if (!BooleanUtils.isTrue(redisOperations.hasKey("context"))) {
                ContextContainer.clearContext();
            }
        } catch (Exception e) {
            log.warn("clear context failed:{}", e.getMessage());
        }
    }
}
