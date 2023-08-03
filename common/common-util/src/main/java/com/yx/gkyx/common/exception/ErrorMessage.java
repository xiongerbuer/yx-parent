package com.yx.gkyx.common.exception;


public enum ErrorMessage {

    NULL_ARGUMENT("`%s`不能为空"),
    FAILED_EXPECTATION("`%s`与预期不符，预期为：%s，实际为：%s"),
    CHECKED_EXCEPTION_RETHROW("%s - 原因为： %s");

    private final String messageTemplate;

    ErrorMessage(String messageTemplate) {
        this.messageTemplate = messageTemplate;
    }

    @Override
    public String toString() {
        return messageTemplate;
    }

}
