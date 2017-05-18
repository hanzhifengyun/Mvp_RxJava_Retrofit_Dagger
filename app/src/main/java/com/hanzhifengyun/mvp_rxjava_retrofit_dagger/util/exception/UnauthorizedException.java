package com.hanzhifengyun.mvp_rxjava_retrofit_dagger.util.exception;

/**
 * 用户cookie失效
 */
public class UnauthorizedException extends Exception {
    public UnauthorizedException() {

    }

    public UnauthorizedException(String exceptionMessage) {
        super(exceptionMessage);
    }

    public UnauthorizedException(String exceptionMessage, Throwable throwable) {
        super(exceptionMessage, throwable);
    }

    public UnauthorizedException(Throwable throwable) {
        super(throwable);
    }

    @Override
    public String getMessage() {
        if (super.getMessage() == null) {
            return "Unauthorized";
        }
        return super.getMessage();
    }
}
