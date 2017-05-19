package com.hanzhifengyun.mvp_rxjava_retrofit_dagger.base;

/**
 * View基类
 */
public interface BaseView {

    /**
     * 显示网络不可用
     */
    void showNetWorkError();

    /**
     * 显示错误信息
     *
     * @param message
     */
    void showErrorMessage(String message);

    /**
     * 显示加载弹框
     */
    void showLoadingDialog(boolean cancelable);

    /**
     * 隐藏加载弹框
     */
    void hideLoadingDialog();


    /**
     * 退出该界面
     */
    void finishActivity();

}