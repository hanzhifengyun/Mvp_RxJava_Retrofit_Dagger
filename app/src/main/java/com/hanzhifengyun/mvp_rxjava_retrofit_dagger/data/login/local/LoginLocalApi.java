package com.hanzhifengyun.mvp_rxjava_retrofit_dagger.data.login.local;

import android.content.Context;
import android.support.annotation.NonNull;

import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.common.rx.OnceObserver;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.model.User;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

import static com.hanzhifengyun.mvp_rxjava_retrofit_dagger.util.Preconditions.checkNotNull;


@Singleton
public class LoginLocalApi implements ILoginLocalApi {

    private List<User> mCacheUsers;


    @Inject
    public LoginLocalApi(@NonNull Context context) {
        checkNotNull(context, "context cannot be null");
        mCacheUsers = new ArrayList<>();
    }


    @Override
    public void loginOutLocal() {
        getUser().subscribe(new OnceObserver<User>() {
            @Override
            protected void onResponse(User user) {
                user.setLogin(false);
            }
        });
    }

    @Override
    public void saveUser(@NonNull final User user) {
        if (!mCacheUsers.contains(user)) {
            mCacheUsers.add(user);
        }
    }

    @Override
    public Observable<User> getUser() {
        User user = null;
        for (User cacheUser : mCacheUsers) {
            if (cacheUser.isLogin()) {
                user = cacheUser;
                return Observable.just(user);
            }
        }
        return Observable.empty();
    }
}
