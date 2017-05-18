package com.hanzhifengyun.mvp_rxjava_retrofit_dagger.common.loading;


import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.base.BaseView;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.util.exception.MyRuntimeException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 一次性接收者，封装BaseView loading and show errorMessage
 * 需在主线程调用，并且回调在主线程
 */
public abstract class OnceLoadingObserver<T> implements Observer<T> {
    private BaseView mBaseView;
    private boolean cancelable;
    private boolean isLoading;
    private Disposable mDisposable;

    public OnceLoadingObserver(BaseView baseView, boolean cancelable) {
        this.mBaseView = baseView;
        this.cancelable = cancelable;
    }

    public OnceLoadingObserver(BaseView baseView) {
        //默认可以取消
        this(baseView, true);
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (mBaseView != null) {
            isLoading = true;
            mBaseView.showLoadingDialog(cancelable);
        }
        mDisposable = d;
    }


    @Override
    public void onError(Throwable e) {
        if (mBaseView != null) {
            isLoading = false;
            mBaseView.hideLoadingDialog();
        }
        if (e instanceof ConnectException || e instanceof SocketTimeoutException) {
            if (mBaseView != null) {
                mBaseView.showNetWorkError();
            }
            onNetworkError();
        } else if (e instanceof ClassCastException || e instanceof NullPointerException) {
            onResultIsNull(e);
        } else if (e instanceof MyRuntimeException) {
            if (mBaseView != null && e.getMessage() != null) {
                mBaseView.showErrorMessage(e.getMessage());
            }
        }

        onResponseError(e);
        if (!mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    @Override
    public void onNext(T value) {
        if (mBaseView != null && isLoading) {
            isLoading = false;
            mBaseView.hideLoadingDialog();
        }
        onResponse(value);
    }

    protected abstract void onResponse(T value);


    public void onResultIsNull(Throwable e) {

    }

    public void onResponseError(Throwable e) {

    }

    public void onNetworkError() {
    }

    @Override
    public void onComplete() {
        if (mBaseView != null && isLoading) {
            isLoading = false;
            mBaseView.hideLoadingDialog();
        }
        if (!mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }
}
