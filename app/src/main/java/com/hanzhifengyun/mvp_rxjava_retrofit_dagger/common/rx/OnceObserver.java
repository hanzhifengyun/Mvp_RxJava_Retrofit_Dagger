package com.hanzhifengyun.mvp_rxjava_retrofit_dagger.common.rx;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * 一次性观察者，无论回调哪个都结束观察
 */
public abstract class OnceObserver<T> implements Observer<T> {
    private Disposable mDisposable;

    @Override
    public void onComplete() {
        if (!mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    @Override
    public void onError(Throwable e) {
        if (!mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
        mDisposable = d;
    }

    @Override
    public void onNext(T t) {
        onResponse(t);
    }


    protected abstract void onResponse(T value);
}
