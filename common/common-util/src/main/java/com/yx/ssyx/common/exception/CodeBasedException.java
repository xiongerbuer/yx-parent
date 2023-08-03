package com.yx.ssyx.common.exception;


import com.yx.ssyx.common.vo.IErrorCode;

public class CodeBasedException extends RuntimeException {

    private final IErrorCode code;
    private final Object[] args;

    public CodeBasedException(IErrorCode code, Object... args) {
        this.code = code;
        this.args = args;
    }

    public IErrorCode getErrorMessage() {
        return code;
    }

    @Override
    public String getMessage() {
        if (args == null || args.length == 0)
            return code.getMessage();
        return String.format(code.getMessage(), args);
    }
}
