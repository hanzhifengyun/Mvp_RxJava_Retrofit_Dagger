package com.hanzhifengyun.mvp_rxjava_retrofit_dagger.login;


import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.base.BasePresenter;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.base.BaseView;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.model.User;

public interface LoginContract {
    interface View extends BaseView {

        /**
         * 设置用户名
         *
         * @param userName
         */
        void setUserName(String userName);

        /**
         * 设置密码
         *
         * @param password
         */
        void setPassword(String password);

        /**
         * 设置删除用户名按钮可见
         */
        void showClearUserNameButton();

        /**
         * 设置删除用户名按钮不可见
         */
        void hideClearUserNameButton();

        /**
         * 设置用户名为空
         */
        void setUserNameEmpty();

        /**
         * 设置密码明文
         */
        void setPasswordShow();

        /**
         * 设置密码密文
         */
        void setPasswordHint();

        /**
         * 显示用户名不能为空
         */
        void showUserNameNotEmptyTips();

        /**
         * 跳转HomeActivity
         */
        void startHomeActivity();

    }

    interface Presenter extends BasePresenter<View> {
        /**
         * 用户名改变
         *
         * @param userName
         */
        void onUserNameAfterTextChanged(String userName);

        /**
         * 删除用户名
         */
        void onClearUserNameBtnClick();

        /**
         * 密码明文或者密文显示
         *
         * @param visible
         */
        void showOrHintPassword(boolean visible);

        void login(User user);
    }
}
