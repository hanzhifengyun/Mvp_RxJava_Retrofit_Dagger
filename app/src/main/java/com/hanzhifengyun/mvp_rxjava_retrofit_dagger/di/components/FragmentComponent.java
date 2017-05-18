package com.hanzhifengyun.mvp_rxjava_retrofit_dagger.di.components;

import android.app.Activity;

import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.di.FragmentScope;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.di.modules.FragmentModule;

import dagger.Component;


@FragmentScope
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {


    Activity getActivity();

}
