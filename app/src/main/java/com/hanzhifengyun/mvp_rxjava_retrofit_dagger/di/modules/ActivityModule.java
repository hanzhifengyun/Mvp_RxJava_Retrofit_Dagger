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
package com.hanzhifengyun.mvp_rxjava_retrofit_dagger.di.modules;

import android.app.Activity;

import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.common.http.HttpManager;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.common.http.IHttpManager;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.common.loading.ILoadingView;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.common.loading.LoadingView;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.common.toast.IToastView;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.common.toast.ToastView;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.di.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * A module to wrap the Activity state and expose it to the graph.
 */
@Module
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    /**
     * Expose the activity to dependents in the graph.
     */
    @Provides
    @ActivityScope
    Activity provideActivity() {
        return this.activity;
    }



    @Provides
    @ActivityScope
    ILoadingView provideLoadingView(LoadingView loadingView) {
        return loadingView;
    }


    @Provides
    @ActivityScope
    IToastView provideToastView(ToastView toastView) {
        return toastView;
    }


    @Provides
    @ActivityScope
    IHttpManager provideHttpManager(HttpManager httpManager) {
        return httpManager;
    }
}
