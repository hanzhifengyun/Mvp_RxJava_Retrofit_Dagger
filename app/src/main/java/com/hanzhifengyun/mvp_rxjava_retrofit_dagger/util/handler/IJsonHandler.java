package com.hanzhifengyun.mvp_rxjava_retrofit_dagger.util.handler;

import android.support.annotation.NonNull;

import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;


public interface IJsonHandler {
    <T> T fromJson(String json, Class<T> classOfT) throws JsonSyntaxException;

    <T> T fromJson(String json, Type classOfT) throws JsonSyntaxException;

    String toJson(@NonNull Object object);
}
