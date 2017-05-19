package com.hanzhifengyun.mvp_rxjava_retrofit_dagger.common.http;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.hanzhifengyun.mvp_rxjava_retrofit_dagger.util.Preconditions.checkNotNull;


public class RetrofitFactory {
    private RetrofitFactory() {
    }
    public static Retrofit createGsonWithRxJavaRetrofit(String baseUrl, OkHttpClient okHttpClient) {
        return create(baseUrl, okHttpClient, GsonConverterFactory.create(), RxJava2CallAdapterFactory.create());
    }

    public static Retrofit createStringWithRxJavaRetrofit(String baseUrl, OkHttpClient okHttpClient) {
        return create(baseUrl, okHttpClient, ScalarsConverterFactory.create(), RxJava2CallAdapterFactory.create());
    }



    private static Retrofit create(String baseUrl,
                                   OkHttpClient okHttpClient,
                                   Converter.Factory converterFactory,
                                   CallAdapter.Factory adapterFactory) {
        checkNotNull(baseUrl, "baseUrl cannot be null");
        Retrofit.Builder builder = new Retrofit.Builder();
        if (okHttpClient != null) {
            builder.client(okHttpClient);
        }
        builder.baseUrl(baseUrl);
        if (converterFactory != null) {
            builder.addConverterFactory(converterFactory);
        }
        if (adapterFactory != null) {
            builder.addCallAdapterFactory(adapterFactory);
        }
        return builder.build();
    }
}
