package com.hanzhifengyun.mvp_rxjava_retrofit_dagger.util.exception;

/**
 * 自定义异常
 */
public class MyRuntimeException extends Exception {
    public MyRuntimeException() {
    }

    public MyRuntimeException(String detailMessage) {
        super(detailMessage);
    }

    public MyRuntimeException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public MyRuntimeException(Throwable throwable) {
        super(throwable);
    }


    @Override
    public String getMessage() {
        if (super.getMessage() == null) {
            return "unknown error";
        }
        return super.getMessage();
    }
}
