package com.hanzhifengyun.mvp_rxjava_retrofit_dagger.util.handler;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.util.LogUtil;

import java.lang.reflect.Type;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class GsonHandler implements IJsonHandler {
    private static final String TAG = "GsonHandler";
    private final Gson FROMGSON = new GsonBuilder()
            .create();

    private final Gson TOGSON = new GsonBuilder()
            .create();

    @Inject
    public GsonHandler() {

    }

    @Override
    public <T> T fromJson(String json, Class<T> classOfT) throws JsonSyntaxException {
        try {
            return FROMGSON.fromJson(json, classOfT);
        } catch (JsonSyntaxException e) {
            LogUtil.e(TAG, "fromJson: Json to Object (" + classOfT.getSimpleName() + ") with error");
            throw e;
        }
    }

    @Override
    public <T> T fromJson(String json, Type classOfT) throws JsonSyntaxException {
        try {
            return FROMGSON.fromJson(json, classOfT);
        } catch (JsonSyntaxException e) {
            LogUtil.e(TAG, "fromJson: Json to Object (" + classOfT.getClass().getSimpleName() + ") with error");
            throw e;
        }
    }

    @Override
    public String toJson(@NonNull Object object) {
        return TOGSON.toJson(object);
    }
}
