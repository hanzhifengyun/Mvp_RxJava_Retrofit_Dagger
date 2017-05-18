package com.hanzhifengyun.mvp_rxjava_retrofit_dagger.common.loading;

/**
 * 显示/隐藏 加载中
 */
public interface ILoadingView {

    /**
     * 显示加载中
     */
    void showLoading(boolean cancelable);

    /**
     * 隐藏加载中
     */
    void hideLoading();


    /**
     * @return 是否在显示中
     */
    boolean isShowing();

}
