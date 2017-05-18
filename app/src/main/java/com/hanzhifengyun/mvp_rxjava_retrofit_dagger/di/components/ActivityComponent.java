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

import android.app.Activity;

import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.di.ActivityScope;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.di.modules.ActivityModule;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.login.LoginActivity;

import dagger.Component;

/**
 * A base component upon which fragment's components may depend.
 * Activity-level components should extend this component.
 * <p>
 * Subtypes of ActivityComponent should be decorated with annotation:
 * {@link ActivityScope}
 */
@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {


    //Exposed to sub-graphs.
    Activity getActivity();

    void inject(LoginActivity activity);
}
