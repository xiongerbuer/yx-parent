package com.yx.ssyx.common.http;

import com.yx.ssyx.common.http.annotation.CurrentUser;
import com.yx.ssyx.common.http.mvc.CurrentUserHandlerMethodArgumentResolver;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 例子：
 * <pre><code>
 * class XxxController {
 *     Object someMethod(@CurrentUser UserContext context){
 *         // do something
 *     }
 * }
 * </code></pre>
 *
 * @author liumaomao
 * @see CurrentUser
 * @see CurrentUserHandlerMethodArgumentResolver
 * @since 2022-10-18
 */
@Data
@Schema(description = "用户上下文信息")
public class UserContext {

    @ApiModelProperty(value = "用户类型，数字工厂企业用户：enterprise，运营后台用户：admin")
    private String userType;

    @ApiModelProperty(value = "登陆类型，账号密码登陆：password，手机验证码登陆：verify_code")
    private String loginType;

    @ApiModelProperty(value = "租户id")
    private Long currentTenantId;

    @ApiModelProperty(value = "当前登录用户id")
    private Long currentUserId;

    @ApiModelProperty(value = "当前用户名")
    private String currentUserName;

    @ApiModelProperty(value = "登录账号")
    private String account;

    @ApiModelProperty(value = "手机号")
    private String phone;

}
