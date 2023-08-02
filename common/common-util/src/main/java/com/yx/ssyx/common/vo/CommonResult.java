package com.yx.ssyx.common.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommonResult<T> implements Serializable {

    private boolean status;
    private long code;
    private String message;
    private T data;

    protected CommonResult(long code, String message, T data, boolean status) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.status = status;
    }

    public static <T> CommonResult<T> success(T data) {
        return new CommonResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data, true);
    }

    public static <T> CommonResult<T> success() {
        CommonResult<T> result = new CommonResult();
        result.setCode(200L);
        result.setMessage("success");
        result.setStatus(true);
        return result;
    }

    public static <T> CommonResult<T> success(T data, String message) {
        return new CommonResult(ResultCode.SUCCESS.getCode(), message, data, true);
    }

    public static <T> CommonResult<T> failed(IErrorCode errorCode) {
        return new CommonResult(errorCode.getCode(), errorCode.getMessage(), (Object)null, false);
    }

    public static <T> CommonResult<T> failed(IErrorCode errorCode, String message) {
        return new CommonResult(errorCode.getCode(), message, (Object)null, false);
    }

    public static <T> CommonResult<T> failed(String message) {
        return new CommonResult(ResultCode.FAILED.getCode(), message, (Object)null, false);
    }

    public static <T> CommonResult<T> failed() {
        return failed((IErrorCode)ResultCode.FAILED);
    }

    public static <T> CommonResult<T> validateFailed() {
        return failed((IErrorCode)ResultCode.VALIDATE_FAILED);
    }

    public static <T> CommonResult<T> validateFailed(String message) {
        return new CommonResult(ResultCode.VALIDATE_FAILED.getCode(), message, (Object)null, false);
    }

    public static <T> CommonResult<T> unauthorized(T data) {
        return new CommonResult(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), data, false);
    }

    public static <T> CommonResult<T> forbidden(T data) {
        return new CommonResult(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), data, false);
    }

    public boolean isStatus() {
        return this.status;
    }

    public long getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public T getData() {
        return this.data;
    }

    public void setStatus(final boolean status) {
        this.status = status;
    }

    public void setCode(final long code) {
        this.code = code;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public void setData(final T data) {
        this.data = data;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof CommonResult)) {
            return false;
        } else {
            CommonResult<?> other = (CommonResult)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.isStatus() != other.isStatus()) {
                return false;
            } else if (this.getCode() != other.getCode()) {
                return false;
            } else {
                label40: {
                    Object this$message = this.getMessage();
                    Object other$message = other.getMessage();
                    if (this$message == null) {
                        if (other$message == null) {
                            break label40;
                        }
                    } else if (this$message.equals(other$message)) {
                        break label40;
                    }

                    return false;
                }

                Object this$data = this.getData();
                Object other$data = other.getData();
                if (this$data == null) {
                    if (other$data != null) {
                        return false;
                    }
                } else if (!this$data.equals(other$data)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CommonResult;
    }

    public int hashCode() {
//        boolean PRIME = true;
        int result = 1;
        result = result * 59 + (this.isStatus() ? 79 : 97);
        long $code = this.getCode();
        result = result * 59 + (int)($code >>> 32 ^ $code);
        Object $message = this.getMessage();
        result = result * 59 + ($message == null ? 43 : $message.hashCode());
        Object $data = this.getData();
        result = result * 59 + ($data == null ? 43 : $data.hashCode());
        return result;
    }

    public String toString() {
        return "CommonResult(status=" + this.isStatus() + ", code=" + this.getCode() + ", message=" + this.getMessage() + ", data=" + this.getData() + ")";
    }

    public CommonResult() {
    }

}
