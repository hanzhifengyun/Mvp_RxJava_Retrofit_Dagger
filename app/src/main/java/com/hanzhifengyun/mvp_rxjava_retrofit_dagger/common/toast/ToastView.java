package com.hanzhifengyun.mvp_rxjava_retrofit_dagger.common.toast;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.di.ActivityScope;

import javax.inject.Inject;

/**
 * 显示toast implements {@link IToastView}
 */
@ActivityScope
public class ToastView implements IToastView {

    @Inject
    public ToastView(Context context) {
        this.mContext = context.getApplicationContext();
    }

    private Toast mToast;

    private final Context mContext;

    /**
     * 适用于快速替换toast内容而不用等上一个Toast显示完毕再弹出
     */
    private void showToast(CharSequence text, int duration) {
        if (text == null) {
            text = "";
        }
        if (mToast == null) {
            synchronized (this) {
                if (mToast == null) {
                    mToast = Toast.makeText(mContext, text, duration);
                }
            }
        }
        mToast.setText(text);
        mToast.setDuration(duration);
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.show();
    }

    /**
     * 提示一个新的Toast，需要等上一个Toast提示完才能显示
     */
    private void showNewToast(CharSequence text, int duration) {
        if (text == null) {
            text = "";
        }
        Toast toast = Toast.makeText(mContext, text, duration);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }


    @Override
    public void showShortToast(CharSequence text) {
        showToast(text, Toast.LENGTH_SHORT);
    }

    @Override
    public void showLongToast(CharSequence text) {
        showToast(text, Toast.LENGTH_LONG);
    }

    @Override
    public void showShortNewToast(CharSequence text) {
        showNewToast(text, Toast.LENGTH_SHORT);
    }

    @Override
    public void showLongNewToast(CharSequence text) {
        showNewToast(text, Toast.LENGTH_LONG);
    }

}