/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.hanzhifengyun.mvp_rxjava_retrofit_dagger.base;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.R;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.application.AndroidApplication;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.di.components.ApplicationComponent;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.di.components.DaggerFragmentComponent;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.di.components.FragmentComponent;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.di.modules.FragmentModule;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * BaseFragment for every fragment in this application.
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView {


    @Inject
    protected T mPresenter;
    protected View mView;
    protected Activity mActivity;
    protected Context mContext;
    private Unbinder mUnBinder;


    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
    }


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
        getActivity().finish();
    }

    protected ApplicationComponent getApplicationComponent() {
        return AndroidApplication.getAndroidApplication().getApplicationComponent();
    }


    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .applicationComponent(getApplicationComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    protected FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutResId(), container, false);
        initInjector();
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        mUnBinder = ButterKnife.bind(this, view);
        initEvent();
        if (mPresenter != null) {
            mPresenter.onStart();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }

    }


    protected void showLoading() {
        showLoading(true);
    }


    protected void showLoading(boolean cancelable) {
        if (isFromBaseActivityAndIsAdded()) {
            getBaseActivity().showLoading(cancelable);
        }
    }

    private BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }


    protected void hideLoading() {
        if (isFromBaseActivityAndIsAdded()) {
            getBaseActivity().hideLoading();
        }
    }


    protected void showShortToast(CharSequence text) {
        if (isFromBaseActivityAndIsAdded()) {
            getBaseActivity().showShortToast(text);
        }
    }

    private boolean isFromBaseActivityAndIsAdded() {
        return getActivity() instanceof BaseActivity && isAdded();
    }


    protected void showLongToast(CharSequence text) {
        if (isFromBaseActivityAndIsAdded()) {
            getBaseActivity().showLongToast(text);
        }
    }


    protected void showShortNewToast(CharSequence text) {
        if (isFromBaseActivityAndIsAdded()) {
            getBaseActivity().showShortNewToast(text);
        }
    }


    protected void showLongNewToast(CharSequence text) {
        if (isFromBaseActivityAndIsAdded()) {
            getBaseActivity().showLongNewToast(text);
        }
    }


    protected abstract void initInjector();

    protected abstract int getLayoutResId();

    protected abstract void initEvent();
}
