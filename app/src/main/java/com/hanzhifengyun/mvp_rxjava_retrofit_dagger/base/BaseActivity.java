package com.hanzhifengyun.mvp_rxjava_retrofit_dagger.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.R;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.application.AndroidApplication;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.common.CommonViewRepository;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.di.components.ActivityComponent;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.di.components.ApplicationComponent;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.di.components.DaggerActivityComponent;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.di.modules.ActivityModule;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.Lazy;


/**
 * BaseActivity for every Activity in this application.
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {

    /**
     * 当该页面不需要布局时 return
     */
    protected static final int LAYOUT_ID_NULL = 0;


    @Inject
    protected T mPresenter;

    private Unbinder mUnBinder;

    protected Activity mContext;

    @Inject
    Lazy<CommonViewRepository> mCommonViewRepositoryLazy;

    @Override
    public void showNetWorkError() {
        showShortToast(getString(R.string.network_failure));
    }

    @Override
    public void showErrorMessage(String message) {
        showShortToast(message);
    }

    @Override
    public void showLoadingDialog(boolean cancelable) {
        showLoading(cancelable);
    }

    @Override
    public void hideLoadingDialog() {
        hideLoading();
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;

        int layoutResId = getLayoutResId();
        if (layoutResId != LAYOUT_ID_NULL) {
            setContentView(layoutResId);

            mUnBinder = ButterKnife.bind(this);
        }

        initInjector();

        if (mPresenter != null) {
            mPresenter.attachView(this);
        }

        initEvent();

        if (mPresenter != null) {
            mPresenter.onStart();
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }

        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
    }

    protected ApplicationComponent getApplicationComponent() {
        return AndroidApplication.getAndroidApplication().getApplicationComponent();
    }


    protected void showLoading() {
        showLoading(true);
    }


    protected void showLoading(boolean cancelable) {
        if (!isFinishing() && mCommonViewRepositoryLazy != null) {
            getCommonViewRepositoryLazy().showLoading(cancelable);
        }
    }

    private CommonViewRepository getCommonViewRepositoryLazy() {
        return mCommonViewRepositoryLazy.get();
    }


    protected void hideLoading() {
        if (!isFinishing() && mCommonViewRepositoryLazy != null && getCommonViewRepositoryLazy().isShowing()) {
            getCommonViewRepositoryLazy().hideLoading();
        }
    }


    public void showShortToast(CharSequence text) {
        if (mCommonViewRepositoryLazy != null && !isFinishing()) {
            getCommonViewRepositoryLazy().showShortToast(text);
        }
    }

    /**
     * 提示短时长信息 {@link android.widget.Toast}
     *
     * @param resId string——id
     */
    protected void showShortToast(int resId) {
        showShortToast(getString(resId));
    }


    protected void showLongToast(CharSequence text) {
        if (mCommonViewRepositoryLazy != null && !isFinishing()) {
            getCommonViewRepositoryLazy().showLongToast(text);
        }
    }


    protected void showShortNewToast(CharSequence text) {
        if (mCommonViewRepositoryLazy != null && !isFinishing()) {
            getCommonViewRepositoryLazy().showShortNewToast(text);
        }
    }


    protected void showLongNewToast(CharSequence text) {
        if (mCommonViewRepositoryLazy != null && !isFinishing()) {
            getCommonViewRepositoryLazy().showLongNewToast(text);
        }
    }

    protected <T extends View> T findById(@NonNull View view, @IdRes int resId) {
        return ButterKnife.findById(view, resId);
    }


    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }


    protected abstract void initInjector();

    protected abstract int getLayoutResId();

    protected abstract void initEvent();
}
