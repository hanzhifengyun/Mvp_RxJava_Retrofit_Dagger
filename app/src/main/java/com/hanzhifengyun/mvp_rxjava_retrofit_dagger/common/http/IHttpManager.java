package com.hanzhifengyun.mvp_rxjava_retrofit_dagger.common.http;


public interface IHttpManager {

    /**
     * 显示网络错误Dialog
     */
    void showNetworkFailureDialog();

    /**
     * 判断是否连接网络
     */
    boolean isOnline();
}
