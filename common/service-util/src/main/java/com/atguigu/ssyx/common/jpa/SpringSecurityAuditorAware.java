package com.atguigu.ssyx.common.jpa;

import com.isiiot.apollo.base.exception.CodeBasedException;
import com.isiiot.apollo.base.exception.ErrorMessageEnum;
import com.isiiot.apollo.base.http.ContextContainer;
import com.isiiot.apollo.base.http.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @ClassName SpringSecurityAuditorAware
 * @Description 用户注入
 * @Author tuzhen@isiiot.com
 * @Date 2023-04-23 17:02
 */
@Slf4j
@Component
public class SpringSecurityAuditorAware implements AuditorAware<Long> {

    /**
     * jpa数据库 创建人修改人id
     *
     * @return 当前登录用户id 当前为默认1
     */
    @NonNull
    @Override
    public Optional<Long> getCurrentAuditor() {

        try {
            UserContext userContext = ContextContainer.checkLogin();
            return Optional.of(userContext.getCurrentUserId());
        } catch (IllegalArgumentException error) {
            error.printStackTrace();
            log.info("暂无登录信息");
            throw new CodeBasedException(ErrorMessageEnum.SERVER_EXCEPTION, "未检测到登录用户信息");
        }
    }
}
