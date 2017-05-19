package com.hanzhifengyun.mvp_rxjava_retrofit_dagger.di.modules;

import android.content.Context;

import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.common.http.OkHttpFactory;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.common.http.RetrofitFactory;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.di.ResultType;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.di.StringType;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * 网络框架
 */
@Module
public class HttpModule {

    private final String baseUrl;
    private final boolean isDebug;

    public HttpModule(String baseUrl, boolean isDebug) {
        this.baseUrl = baseUrl;
        this.isDebug = isDebug;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Context context) {
        return OkHttpFactory.create(context, isDebug);
    }

    @Provides
    @ResultType //表示直接返回对象类型
    @Singleton
    Retrofit provideResultRetrofit(OkHttpClient okHttpClient) {
        return RetrofitFactory.createGsonWithRxJavaRetrofit(baseUrl, okHttpClient);
    }

    @Provides
    @StringType //表示返回Json String类型
    @Singleton
    Retrofit provideStringRetrofit(OkHttpClient okHttpClient) {
        return RetrofitFactory.createStringWithRxJavaRetrofit(baseUrl, okHttpClient);
    }





}
