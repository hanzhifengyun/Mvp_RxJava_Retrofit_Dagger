package com.hanzhifengyun.mvp_rxjava_retrofit_dagger.util.exception;


/**
 * 本地用户信息获取失败
 */
public class UserNullException extends Exception {
    public UserNullException() {

    }

    public UserNullException(String exceptionMessage) {
        super(exceptionMessage);
    }

    public UserNullException(String exceptionMessage, Throwable throwable) {
        super(exceptionMessage, throwable);
    }

    public UserNullException(Throwable throwable) {
        super(throwable);
    }

    @Override
    public String getMessage() {
        if (super.getMessage() == null) {
            return "用户信息为空，请重新登录";
        }
        return super.getMessage();
    }
}
