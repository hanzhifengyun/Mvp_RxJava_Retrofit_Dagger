package com.hanzhifengyun.mvp_rxjava_retrofit_dagger.di.modules;


import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.data.login.local.ILoginLocalApi;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.data.login.local.LoginLocalApi;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.data.login.remote.ILoginRemoteApi;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.data.login.remote.LoginRemoteApi;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.data.login.remote.LoginRemoteService;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.di.ResultType;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;


@Module
public class UserModule {

    @Provides
    @Singleton
    LoginRemoteService provideLoginRemoteService(@ResultType Retrofit retrofit) {
        return retrofit.create(LoginRemoteService.class);
    }

    @Singleton
    @Provides
    ILoginLocalApi provideLoginLocalApi(LoginLocalApi loginLocalApi) {
        return loginLocalApi;
    }

    @Singleton
    @Provides
    ILoginRemoteApi provideLoginRemoteApi(LoginRemoteApi loginRemoteApi) {
        return loginRemoteApi;
    }
}
