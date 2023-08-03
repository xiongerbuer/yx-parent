package com.yx.gkyx.common.exception;

public class InformativeException extends RuntimeException {

    private boolean informFrontend = true;

    public InformativeException(String message) {
        super(message);
    }

    public InformativeException(String message, boolean informFrontend) {
        super(message);
        this.informFrontend = informFrontend;
    }

    public boolean isInformFrontend() {
        return informFrontend;
    }
}
