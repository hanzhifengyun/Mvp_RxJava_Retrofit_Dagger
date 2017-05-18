package com.hanzhifengyun.mvp_rxjava_retrofit_dagger.common.loading;


import android.app.Activity;
import android.content.Context;

import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.R;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.di.ActivityScope;

import javax.inject.Inject;

/**
 * 实现提示加载中Dialog {@link ILoadingView}
 */
@ActivityScope
public class LoadingView implements ILoadingView {

    private Context mContext;
    private LoadingDialog mLoadingDialog;
    private int taskCount;

    @Inject
    public LoadingView(Activity activity) {
        mContext = activity;
        mLoadingDialog = new LoadingDialog(mContext, R.style.Loading_Dialog);
        taskCount = 0;
    }

    @Override
    public void showLoading(boolean cancelable) {
        if (mLoadingDialog != null) {
            if (!mLoadingDialog.isShowing()) {
                mLoadingDialog.show();
            }
            taskCount++;
        }
    }

    @Override
    public void hideLoading() {
        if (mLoadingDialog != null) {
            taskCount--;
            if (mLoadingDialog.isShowing() && taskCount <= 0) {
                mLoadingDialog.dismiss();
            }
        }
    }

    @Override
    public boolean isShowing() {
        return mLoadingDialog != null && mLoadingDialog.isShowing();
    }

}
