package com.hanzhifengyun.mvp_rxjava_retrofit_dagger.login;


import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.base.BasePresenter;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.base.BaseView;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.model.User;

/**
 * 登录契约
 */
public interface LoginContract {
    interface View extends BaseView {

        /**
         * 设置用户名
         *
         * @param userName 用户名
         */
        void setUserName(String userName);

        /**
         * 设置密码
         *
         * @param password 用户名
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
        void showPassword();

        /**
         * 设置密码密文
         */
        void hidePassword();

        /**
         * 显示用户名不能为空
         */
        void showUserNameEmpty();

        /**
         * 跳转HomeActivity
         */
        void openHomePage();

    }

    interface Presenter extends BasePresenter<View> {
        /**
         * 当输入框用户名改变
         *
         * @param userName 用户名
         */
        void onEdtUserNameChanged(String userName);

        /**
         * 当删除用户名按钮被点击
         */
        void onBtnClearUserNameClick();

        /**
         * 当显示隐藏密码按钮被点击
         *
         * @param needShow 是否需要显示
         */
        void onBtnShowOrHidePasswordClick(boolean needShow);

        /**
         * 当登录按钮被点击
         * @param user 用户信息
         */
        void login(User user);
    }
}
