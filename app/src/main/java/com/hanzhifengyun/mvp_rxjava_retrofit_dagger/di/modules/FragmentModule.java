package com.hanzhifengyun.mvp_rxjava_retrofit_dagger.di.modules;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.di.FragmentScope;

import dagger.Module;
import dagger.Provides;


@Module
public class FragmentModule {

    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    public Activity provideActivity() {
        return fragment.getActivity();
    }
}
