package com.hanzhifengyun.mvp_rxjava_retrofit_dagger.common.toast;

/**
 * 信息提示
 */
public interface IToastView {

    /**
     * 提示短时长信息 {@link android.widget.Toast}
     *
     * @param text 信息
     */
    void showShortToast(CharSequence text);


    /**
     * 提示长时长信息 {@link android.widget.Toast}
     *
     * @param text 信息
     */
    void showLongToast(CharSequence text);



    /**
     * 提示短时长信息 {@link android.widget.Toast}
     *
     * @param text 信息
     */
    void showShortNewToast(CharSequence text);


    /**
     * 提示长时长信息 {@link android.widget.Toast}
     *
     * @param text 信息
     */
    void showLongNewToast(CharSequence text);

}
