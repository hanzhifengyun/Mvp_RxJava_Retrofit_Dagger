package com.hanzhifengyun.mvp_rxjava_retrofit_dagger.data.login.remote;


import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.model.User;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import okhttp3.RequestBody;

import static com.hanzhifengyun.mvp_rxjava_retrofit_dagger.util.Preconditions.checkNotNull;


@Singleton
public class LoginRemoteApi implements ILoginRemoteApi {
    private static final String TAG = "LoginRemoteApi";
    private User mUser;


    private LoginRemoteService mLoginRemoteService;


    @Inject
    public LoginRemoteApi(LoginRemoteService loginRemoteService) {
        mLoginRemoteService = checkNotNull(loginRemoteService);
    }


    @Override
    public Observable<User> loginRemote(User user) {
        mUser = user;
//        RequestBody requestBody = getLoginRequestBody(user);
//        return mLoginRemoteService.loginRemote(requestBody)
//                .map(new ResultFunction<User>());
        return Observable.just(mUser);
    }

    private RequestBody getLoginRequestBody(User user) {
        //根据具体接口实现
        return null;
    }


    @Override
    public Observable<Object> logoutRemote() {
//        RequestBody requestBody = getLoginOutRequestBody();
//        return mLoginRemoteService.logoutRemote(requestBody)
//                .map(new ResultFunction<>());
        return Observable.just(new Object());
    }

    private RequestBody getLoginOutRequestBody() {
        //根据具体接口实现
        return null;
    }
}
