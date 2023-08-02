package com.yx.ssyx.common.exception;


import com.yx.ssyx.common.vo.IErrorCode;

import java.util.function.Supplier;

import static java.lang.String.format;


public abstract class SystemExceptions {

    public static void argumentIsNull(String argumentName) {
        throw new IllegalArgumentException(String.format(ErrorMessage.NULL_ARGUMENT.toString(), argumentName));
    }

    public static void argumentNotAsExpected(String argumentName, Object expect, Object actual) {
        throw new IllegalArgumentException(String.format(ErrorMessage.FAILED_EXPECTATION.toString(), argumentName, expect, actual));
    }

    public static void quietInformativeException(String message, Object... args) {
        throw new InformativeException(format(message, args), false);
    }

    public static Supplier<InformativeException> supplyQuietInformativeException(String message, Object... args) {
        return () -> new InformativeException(format(message, args), false);
    }

    public static void informativeException(String message, Object... args) {
        throw new InformativeException(format(message, args));
    }

    public static void parametricException(String message, Object... args) {
        throw new CodeBasedException(ErrorMessageEnum.PARAMETER_ERROR, format(message, args));
    }

    public static Supplier<InformativeException> supplyWrapThrowable(Throwable ex, String message, Object... args) {
        final String realMessage = format(message, args);
        String causeMessage = ex.getMessage();
        while (ex.getCause() != null && causeMessage == null) {
            ex = ex.getCause();
            causeMessage = ex.getMessage();
        }
        final String finalCause = causeMessage;
        return () -> new InformativeException(String.format("%s. cause: %s", realMessage, finalCause));
    }

    public static Supplier<InformativeException> supplyInformativeException(String message, Object... args) {
        return () -> new InformativeException(format(message, args));
    }

    public static void userInformativeException(String message, Object... args) {
        throw new UserInformativeException(format(message, args));
    }

    public static void codeBasedException(IErrorCode code) {
        throw new CodeBasedException(code);
    }

    public static void codeBasedException(IErrorCode code, Object... args) {
        throw new CodeBasedException(code, args);
    }

    public static void rethrow(Exception cause, String explain, Object... args) {
        String reason = format(explain, args);
        Throwable curCause = cause;
        while (curCause.getCause() != null) {
            curCause = curCause.getCause();
        }
        throw new WrapRuntimeException(String.format(ErrorMessage.CHECKED_EXCEPTION_RETHROW.toString(), reason, cause.getMessage()), curCause);
    }

    public static void rethrow(Exception ex) {
        rethrow(ex, "封装异常");
    }

    //非法ID异常
    public static void invalidId(Object id) {
        informativeException("非法的ID %s", id);
    }

    //数据库相关通知性异常
    public static void unableToInsert() {
        informativeException("未插入成功，请检查数据库，或相关日志");
    }

    public static void unableToUpdate() {
        informativeException("未更新成功，请检查数据库，或相关日志");
    }

    public static void unableToDelete() {
        informativeException("未删除成功，请检查数据库，或相关日志");
    }

    public static void notSupported(String message, Object... args) {
        throw new UnsupportedOperationException(format(message, args));
    }
}
