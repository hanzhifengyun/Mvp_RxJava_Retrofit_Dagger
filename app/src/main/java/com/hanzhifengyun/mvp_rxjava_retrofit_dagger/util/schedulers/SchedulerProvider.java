package com.hanzhifengyun.mvp_rxjava_retrofit_dagger.util.schedulers;

import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Provides different types of schedulers.
 */
@Singleton
public class SchedulerProvider implements ISchedulerProvider {


    @Inject
    public SchedulerProvider() {

    }

    @Override
    @NonNull
    public Scheduler computation() {
        return Schedulers.computation();
    }

    @Override
    @NonNull
    public Scheduler io() {
        return Schedulers.io();
    }

    @Override
    @NonNull
    public Scheduler newThread() {
        return Schedulers.newThread();
    }

    @Override
    @NonNull
    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }
}
