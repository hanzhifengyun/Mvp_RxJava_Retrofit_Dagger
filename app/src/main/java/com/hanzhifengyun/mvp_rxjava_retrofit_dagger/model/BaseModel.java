package com.hanzhifengyun.mvp_rxjava_retrofit_dagger.model;


public class BaseModel<T> {
    private long id;
    private ErrorBean error;
    private T result;

    public T getResult() {
        return result;
    }

    public BaseModel setResult(T result) {
        this.result = result;
        return this;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ErrorBean getError() {
        return error;
    }

    public void setError(ErrorBean error) {
        this.error = error;
    }


    /**
     * 是否请求成功
     */
    public boolean isSuccessful() {
        return getError() == null;
    }


    /**
     * 请求失败时是否因为cookie失效
     */
    public boolean isUnauthorized() {
        return !isSuccessful() && getError().code == ErrorBean.CODE_UNAUTHORIZED;
    }

    public static class ErrorBean {

        /**
         * cookie失效
         */
        public static final int CODE_UNAUTHORIZED = 401;

        private int code;
        private String message;
        private String detail;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }
    }
}
