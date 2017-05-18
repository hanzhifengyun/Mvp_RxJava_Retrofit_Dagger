package com.hanzhifengyun.mvp_rxjava_retrofit_dagger.login;

import android.support.annotation.NonNull;

import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.base.BaseRxJavaPresenter;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.common.loading.OnceLoadingObserver;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.common.rx.OnceObserver;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.data.login.LoginRepository;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.model.User;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.util.TextUtil;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.util.schedulers.ISchedulerProvider;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

import static com.hanzhifengyun.mvp_rxjava_retrofit_dagger.util.Preconditions.checkNotNull;


/**
 * 登录
 */

public class LoginPresenter extends BaseRxJavaPresenter<LoginContract.View> implements LoginContract.Presenter {
    private LoginRepository mLoginRepository;
    private ISchedulerProvider mSchedulerProvider;

    @Inject
    public LoginPresenter(@NonNull LoginRepository loginRepository,
                          @NonNull ISchedulerProvider schedulerProvider) {
        mLoginRepository = checkNotNull(loginRepository, "loginRepository cannot be null");
        mSchedulerProvider = checkNotNull(schedulerProvider, "schedulerProvider cannot be null");
    }

    @Override
    public void onStart() {
       mLoginRepository.getUser()
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(new OnceObserver<User>() {
                    @Override
                    protected void onResponse(User value) {
                        mView.setUserName(value.getName());
                    }
                });

    }

    @Override
    public void onUserNameAfterTextChanged(String userName) {
        if (!TextUtil.isEmpty(userName)) {
            mView.showClearUserNameButton();
        } else {
            mView.hideClearUserNameButton();
        }
    }

    @Override
    public void onClearUserNameBtnClick() {
        mView.setUserNameEmpty();
    }

    @Override
    public void showOrHintPassword(boolean visible) {
        if (visible) {
            mView.setPasswordShow();
        } else {
            mView.setPasswordHint();
        }
    }

    @Override
    public void login(User user) {
        if (user.isUserNameEmpty()) {
            mView.showUserNameNotEmptyTips();
            return;
        }
        mLoginRepository.loginRemote(user)
                .subscribeOn(mSchedulerProvider.io())
                .doOnNext(new Consumer<User>() {
                    @Override
                    public void accept(User value) throws Exception {
                        value.setLogin(true);
                        mLoginRepository.saveUser(value);
                    }
                })
                .observeOn(mSchedulerProvider.ui())
                .subscribe(new OnceLoadingObserver<User>(mView) {
                    @Override
                    protected void onResponse(User value) {
                        mView.startHomeActivity();
                        mView.finishActivity();
                    }
                });
    }

}
