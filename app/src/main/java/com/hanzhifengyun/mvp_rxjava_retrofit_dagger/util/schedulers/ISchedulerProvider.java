package com.hanzhifengyun.mvp_rxjava_retrofit_dagger.util.schedulers;

import android.support.annotation.NonNull;

import io.reactivex.Scheduler;


/**
 * Allow providing different types of {@link Scheduler}s.
 */
public interface ISchedulerProvider {

    /**
     * @return 计算线程
     */
    @NonNull
    Scheduler computation();

    /**
     * @return 可复用的子线程
     */
    @NonNull
    Scheduler io();

    /**
     * @return Android主线程
     */
    @NonNull
    Scheduler ui();

    /**
     * @return 新的子线程
     */
    @NonNull
    Scheduler newThread();
}
