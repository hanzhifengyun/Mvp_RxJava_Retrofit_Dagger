package com.hanzhifengyun.mvp_rxjava_retrofit_dagger.common.rx;


import android.support.annotation.NonNull;

import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.data.login.LoginRepository;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.model.User;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.util.exception.UnauthorizedException;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.util.exception.UserNullException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;


public class CookieRetryWhenFunction implements Function<Observable<Throwable>, Observable<?>> {

    private LoginRepository mLoginRepository;
    private int count;

    private static final int COUNT_MAX = 3;

    public CookieRetryWhenFunction(@NonNull LoginRepository loginRepository) {
        this.mLoginRepository = loginRepository;
        count = 0;
    }

    @Override
    public Observable<?> apply(Observable<Throwable> throwableObservable) throws Exception {
        return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Throwable throwable) throws Exception {
                if (throwable instanceof UnauthorizedException) {
                    if (count++ < COUNT_MAX) {
                        return mLoginRepository.getUser()
                                .doOnError(new Consumer<Throwable>() {
                                    @Override
                                    public void accept(Throwable throwable) throws Exception {
                                        Observable.error(new UserNullException());
                                    }
                                })
                                .flatMap(new Function<User, ObservableSource<?>>() {
                                    @Override
                                    public ObservableSource<?> apply(User user) throws Exception {
                                        return mLoginRepository.loginRemote(user);
                                    }
                                });
                    }
                }
                return Observable.error(throwable);
            }
        });
    }
}
