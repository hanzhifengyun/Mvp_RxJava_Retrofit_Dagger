package com.hanzhifengyun.mvp_rxjava_retrofit_dagger.base;


/**
 * Presenter基类
 */
public interface BasePresenter<T extends BaseView>{

    void attachView(T view);

    void detachView();

    void onStart();
}