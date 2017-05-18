/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hanzhifengyun.mvp_rxjava_retrofit_dagger.application;


import android.app.Application;

import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.BuildConfig;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.IRestApi;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.di.components.ApplicationComponent;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.di.components.DaggerApplicationComponent;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.di.modules.ApplicationModule;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.di.modules.HttpModule;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.util.LogUtil;


/**
 * Android Main Application
 */
public class AndroidApplication extends Application {

    /**
     * 单例在ApplicationComponent中声明
     */
    private ApplicationComponent mApplicationComponent;

    public static AndroidApplication getAndroidApplication() {
        return mAndroidApplication;
    }

    private static AndroidApplication mAndroidApplication;

    @Override
    public void onCreate() {
        super.onCreate();

        mAndroidApplication = this;


        //初始化依赖注入
        initializeInjector();

        LogUtil.setLevel(BuildConfig.DEBUG ? LogUtil.VERBOSE : LogUtil.NOTHING);
    }

    public void initializeInjector() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .httpModule(getHttpModule())
                .build();
    }


    private HttpModule getHttpModule() {
        return new HttpModule(IRestApi.URL_API_BASE, BuildConfig.DEBUG);
    }


    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

}
