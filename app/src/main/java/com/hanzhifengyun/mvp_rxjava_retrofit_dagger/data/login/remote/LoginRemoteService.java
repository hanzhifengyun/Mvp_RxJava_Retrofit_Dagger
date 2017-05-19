package com.hanzhifengyun.mvp_rxjava_retrofit_dagger.data.login.remote;


import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.IRestApi;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.model.BaseModel;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.model.User;

import javax.inject.Singleton;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;


@Singleton
public interface LoginRemoteService {

    /**
     * 登录
     */
    @POST(IRestApi.URL_API_POST)//替换真实接口后缀
    Observable<BaseModel<User>> loginRemote(@Body RequestBody requestBody);



    /**
     * 退出
     */
    @POST(IRestApi.URL_API_POST)
    Observable<BaseModel<Object>> logoutRemote(@Body RequestBody requestBody);


}
