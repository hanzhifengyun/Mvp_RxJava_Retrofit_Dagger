package com.hanzhifengyun.mvp_rxjava_retrofit_dagger.common.http;


import android.text.TextUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public final class CookieInterceptor implements Interceptor {

    private String cookie;


    public CookieInterceptor() {

    }


    private String getResponseCookie(List<String> cookieList) {
        String cooker = "";
        for (String cookie : cookieList) {
            if (cookie.contains("EasyAuth")) {
                cooker = cookie.substring(0, cookie.indexOf(";"));
            }
        }
        return cooker;
    }

    private void setCookie(String responseCookie) {
        cookie = responseCookie;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        //添加cookie
        if (!TextUtils.isEmpty(cookie)) {
            request = request.newBuilder()
                    .header("cookie", cookie)
                    .header("user-agent", "android")
                    .build();
        } else {
            //为了App和浏览器用户分开设置授权站点数，App登录操作的时候需要设置一下User-Agent http头,App以[android]开始
            request = request.newBuilder()
                    .header("User-Agent", "[android] OkHttp")
                    .build();
        }

        Response response;
        try {
            response = chain.proceed(request);
            if (response.headers("Set-Cookie").size() > 0) {
                setCookie(getResponseCookie(response.headers("Set-Cookie")));
            }
        } catch (Exception e) {

            throw e;
        }

        return response;
    }
}

