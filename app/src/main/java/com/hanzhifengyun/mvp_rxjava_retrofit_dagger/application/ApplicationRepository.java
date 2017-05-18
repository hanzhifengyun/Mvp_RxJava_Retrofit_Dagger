package com.hanzhifengyun.mvp_rxjava_retrofit_dagger.application;


import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ApplicationRepository {

    private Context mContext;

    @Inject
    public ApplicationRepository(Context androidApplication) {
        mContext = androidApplication;
    }

    public void init() {

    }

}
