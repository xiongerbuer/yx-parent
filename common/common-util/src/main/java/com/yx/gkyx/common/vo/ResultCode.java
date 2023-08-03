package com.yx.gkyx.common.vo;

public enum ResultCode implements IErrorCode {

    SUCCESS(200L, "操作成功"),
    FAILED(500L, "操作失败"),
    VALIDATE_FAILED(404L, "参数检验失败"),
    UNAUTHORIZED(401L, "暂未登录或token已经过期"),
    FORBIDDEN(403L, "没有相关权限");

    private long code;
    private String message;

    public long getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    private ResultCode() {
    }

    private ResultCode(final long code, final String message) {
        this.code = code;
        this.message = message;
    }

}
