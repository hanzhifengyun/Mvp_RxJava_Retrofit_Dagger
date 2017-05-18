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
package com.hanzhifengyun.mvp_rxjava_retrofit_dagger.di.components;

import android.content.Context;

import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.application.AndroidApplication;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.application.ApplicationRepository;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.data.login.LoginRepository;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.di.modules.ApplicationModule;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.di.modules.HttpModule;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.di.modules.UserModule;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.util.handler.IJsonHandler;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.util.schedulers.ISchedulerProvider;

import javax.inject.Singleton;

import dagger.Component;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = {ApplicationModule.class,
        HttpModule.class,
        UserModule.class})
public interface ApplicationComponent {


    void inject(AndroidApplication application);

    /**
     * @return 上下文
     */
    Context getContext();

    /**
     * @return 线程调度器
     */
    ISchedulerProvider getSchedulerProvider();


    ApplicationRepository getApplicationRepository();

    LoginRepository getLoginRepository();

    IJsonHandler getJsonHandler();

}
