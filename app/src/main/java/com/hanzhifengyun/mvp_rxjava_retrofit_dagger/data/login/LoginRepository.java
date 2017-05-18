package com.hanzhifengyun.mvp_rxjava_retrofit_dagger.data.login;

import android.support.annotation.NonNull;

import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.common.rx.CheckCookieFunction;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.common.rx.CookieRetryWhenFunction;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.common.rx.ResultFunction;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.data.login.local.ILoginLocalApi;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.data.login.remote.ILoginRemoteApi;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.model.BaseModel;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.model.User;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

import static com.hanzhifengyun.mvp_rxjava_retrofit_dagger.util.Preconditions.checkNotNull;


/**
 * 登录管理者
 */
@Singleton
public class LoginRepository implements ILoginLocalApi, ILoginRemoteApi {

    private static final String TAG = "LoginRepository";


    private ILoginLocalApi mLoginLocalApi;
    private ILoginRemoteApi mLoginRemoteApi;



    @Inject
    public LoginRepository(@NonNull ILoginLocalApi loginLocalApi,
                           @NonNull ILoginRemoteApi userRemoteApi) {
        mLoginLocalApi = checkNotNull(loginLocalApi);
        mLoginRemoteApi = checkNotNull(userRemoteApi);
    }


    @Override
    public void loginOutLocal() {
        mLoginLocalApi.loginOutLocal();
    }

    @Override
    public void saveUser(@NonNull User user) {
        checkNotNull(user);
        mLoginLocalApi.saveUser(user);
    }

    @Override
    public Observable<User> getUser() {
        return mLoginLocalApi.getUser();
    }

    @Override
    public Observable<User> loginRemote(User user) {
        return mLoginRemoteApi.loginRemote(user);
    }

    @Override
    public Observable<Object> logoutRemote() {
        return mLoginRemoteApi.logoutRemote();
    }

    public <T> Observable<T> getCheckCookieObservable(Observable<BaseModel<T>> observable) {
        return observable.flatMap(new CheckCookieFunction<T>())
                .retryWhen(new CookieRetryWhenFunction(this))
                .map(new ResultFunction<T>());
    }
}
