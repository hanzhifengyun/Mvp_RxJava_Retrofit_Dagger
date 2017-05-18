/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hanzhifengyun.mvp_rxjava_retrofit_dagger.data.login.local;

import android.support.annotation.NonNull;

import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.model.User;

import io.reactivex.Observable;


public interface ILoginLocalApi {

    /**
     * 用户退出登录
     */
    void loginOutLocal();

    /**
     * @param user 当前用户信息保存本地
     */
    void saveUser(@NonNull User user);


    /**
     * @return 获取当前登录用户信息
     */
    Observable<User> getUser();
}
