package com.hanzhifengyun.mvp_rxjava_retrofit_dagger.login;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;

import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.R;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.base.BaseActivity;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.home.HomeActivity;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.model.User;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View{

    @BindView(R.id.iv_login_username_delete)
    ImageView mIvLoginUsernameDelete;
    @BindView(R.id.edt_login_username)
    EditText mEdtLoginUsername;
    @BindView(R.id.edt_login_password)
    EditText mEdtLoginPassword;
    @BindView(R.id.chb_login_password_show)
    CheckBox mChbLoginPasswordShow;

    public static void start(Context context) {
        Intent starter = new Intent(context, LoginActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void initInjector() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initEvent() {
        mEdtLoginUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mPresenter.onEdtUserNameChanged(s.toString());
            }
        });

        mChbLoginPasswordShow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPresenter.onBtnShowOrHidePasswordClick(isChecked);
            }
        });


    }



    @Override
    public void setUserName(String userName) {
        mEdtLoginUsername.setText(userName);
        mEdtLoginUsername.setSelection(userName.length());
    }

    @Override
    public void setPassword(String password) {
        mEdtLoginPassword.setText(password);
        mEdtLoginPassword.setSelection(password.length());
    }

    @Override
    public void showClearUserNameButton() {
        mIvLoginUsernameDelete.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideClearUserNameButton() {
        mIvLoginUsernameDelete.setVisibility(View.GONE);
    }

    @Override
    public void setUserNameEmpty() {
        mEdtLoginUsername.setText(R.string.empty_string);
    }

    @Override
    public void showPassword() {
        //显示密码
        mEdtLoginPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        mEdtLoginPassword.setSelection(mEdtLoginPassword.getText().length());
        mEdtLoginPassword.requestFocus();
    }

    @Override
    public void hidePassword() {
        //隐藏密码
        mEdtLoginPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        mEdtLoginPassword.setSelection(mEdtLoginPassword.getText().length());
        mEdtLoginPassword.requestFocus();
    }

    @Override
    public void showUserNameEmpty() {
        showShortToast(getString(R.string.username_not_empty));
    }

    @Override
    public void openHomePage() {
        HomeActivity.start(this);
    }


    @OnClick({R.id.iv_login_username_delete, R.id.btn_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_login_username_delete:
                mPresenter.onBtnClearUserNameClick();
                break;
            case R.id.btn_login:
                mPresenter.login(getUser());
                break;
        }
    }

    private User getUser() {
        User user = new User();
        user.setName(getUserName())
                .setPassword(getPassword());
        return user;
    }

    @NonNull
    private String getPassword() {
        return mEdtLoginPassword.getText().toString().trim();
    }

    @NonNull
    private String getUserName() {
        return mEdtLoginUsername.getText().toString().trim();
    }
}
