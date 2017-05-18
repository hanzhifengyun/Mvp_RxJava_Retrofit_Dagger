package com.hanzhifengyun.mvp_rxjava_retrofit_dagger.login;


import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.data.login.LoginRepository;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.model.User;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.util.exception.MyRuntimeException;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.util.schedulers.ISchedulerProvider;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.util.schedulers.ImmediateSchedulerProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.net.ConnectException;

import io.reactivex.Observable;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the implementation of {@link LoginPresenter}
 */
public class LoginPresenterTest {


    @Mock
    private LoginRepository mLoginRepository;

    @Mock
    private LoginContract.View mLoginView;


    private LoginPresenter mLoginPresenter;

    private User mUser;


    private User mEmptyNameUser;

    @Before
    public void setupLoginPresenter() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        // Make the sure that all schedulers are immediate.
        ISchedulerProvider schedulerProvider = new ImmediateSchedulerProvider();

        // Get a reference to the class under test
        mLoginPresenter = new LoginPresenter(mLoginRepository, schedulerProvider);
        mLoginPresenter.attachView(mLoginView);

        String mUserName, mUserPassword;
        mUserName = "magic";

        mUserPassword = "123";


        mUser = new User();
        mUser.setName(mUserName)
                .setPassword(mUserPassword);


        mEmptyNameUser = new User();
        mEmptyNameUser.setPassword(mUserPassword);
    }

    @After
    public void destroyLoginPresenter() {
        mLoginPresenter.detachView();
    }


    /**
     * 测试 onStart()
     * 场景：第一次进入登录页面
     * 验证：无操作
     */
    @Test
    public void onStartFirst() {
        when(mLoginRepository.getUser()).thenReturn(Observable.<User>empty());

        mLoginPresenter.onStart();

        verify(mLoginRepository).getUser();
        verify(mLoginView, never()).setUserName(anyString());
        verify(mLoginView, never()).setPassword(anyString());
    }

    /**
     * 测试 onStart()
     * 场景：用户已经登陆过，再次进入登录页面
     * 验证：设置保存的用户名
     */
    @Test
    public void onStartNotFirst() {

        when(mLoginRepository.getUser()).thenReturn(Observable.just(mUser));
        when(mLoginRepository.loginRemote(mUser)).thenReturn(Observable.just(mUser));

        mLoginPresenter.onStart();

        verify(mLoginRepository).getUser();
        verify(mLoginView).setUserName(mUser.getName());
    }


    /**
     * 测试 onUserNameAfterTextChanged()
    * 场景：用户名框为空时
    * 验证：清除用户名按钮不显示
    */
    @Test
    public void onUserNameAfterTextChangedEmpty() {
        mLoginPresenter.onUserNameAfterTextChanged("");

        verify(mLoginView).hideClearUserNameButton();
    }

    /**
     * 测试 onUserNameAfterTextChanged()
     * 场景：用户名框不为空时
     * 验证：清除用户名按钮显示
     */
    @Test
    public void onUserNameAfterTextChangedNotEmpty() {
        mLoginPresenter.onUserNameAfterTextChanged("a");

        verify(mLoginView).showClearUserNameButton();
    }

    /**
     * 测试 onClearUserNameBtnClick()
     * 场景：用户点击清空用户名
     * 验证：清空用户名
     */
    @Test
    public void onClearUserNameBtnClick() {
        mLoginPresenter.onClearUserNameBtnClick();

        verify(mLoginView).setUserNameEmpty();
    }

    /**
     * 测试 showOrHintPassword(true)
     * 场景：用户点击显示密码
     * 验证：密码明文显示
     */
    @Test
    public void onShowPasswordButtonClickTrue() {
        mLoginPresenter.showOrHintPassword(true);

        verify(mLoginView).setPasswordShow();
    }


    /**
     * 测试 showOrHintPassword(false)
     * 场景：用户点击隐藏密码
     * 验证：密码隐藏显示
     */
    @Test
    public void onShowPasswordButtonClickFalse() {
        mLoginPresenter.showOrHintPassword(false);

        verify(mLoginView).setPasswordHint();
    }

    /**
     * 测试 login(name, password)
     * 场景：用户点击登录按钮，但没有输入用户名
     * 验证：提示用户用户名为空 且没有调用登录接口
     */
    @Test
    public void loginWithUserNameIsEmpty() {
        mLoginPresenter.login(mEmptyNameUser);

        verify(mLoginView).showUserNameNotEmptyTips();
        verify(mLoginRepository, never()).loginRemote(mEmptyNameUser);
    }


    /**
     * 测试 login(name, password)
     * 场景：用户点击登录按钮并且用户名不为空
     * 验证：不提示用户用户名为空, 弹加载框，调用登录接口
     */
    @Test
    public void loginWithUserNameIsNotEmpty() {
        when(mLoginRepository.loginRemote(mUser)).thenReturn(Observable.just(mUser));

        mLoginPresenter.login(mUser);

        verify(mLoginView, never()).showUserNameNotEmptyTips();

        verify(mLoginView).showLoadingDialog(anyBoolean());

        verify(mLoginRepository).loginRemote(mUser);

    }

    /**
     * 测试 login(name, password)
     * 场景：用户点击登录按钮并且调用登录接口， 返回网络错误信息
     * 验证：提示网络错误信息, 取消加载框
     */
    @Test
    public void loginWithNetworkError() {
        when(mLoginRepository.loginRemote(mUser)).thenReturn(Observable.<User>error(new ConnectException()));

        mLoginPresenter.login(mUser);

        verify(mLoginView).showNetWorkError();

        verify(mLoginView).hideLoadingDialog();
    }


    /**
     * 测试 login(name, password)
     * 场景：用户点击登录按钮并且调用登录接口, 返回用户名密码错误等错误信息
     * 验证：提示用户名密码错误等错误信息, 取消加载框
     */
    @Test
    public void loginWithError() {
        String errorMessage = "error";
        when(mLoginRepository.loginRemote(mUser)).thenReturn(Observable.<User>error(new MyRuntimeException(errorMessage)));

        mLoginPresenter.login(mUser);

        verify(mLoginView).showErrorMessage(errorMessage);

        verify(mLoginView).hideLoadingDialog();
    }


    /**
     * 测试 login(name, password)
     * 场景：用户点击登录按钮并且调用登录接口成功
     * 验证：保存用户信息，不显示错误信息， 隐藏加载框  打开主页面， 关闭登录页面
     */
    @Test
    public void loginOk() {
        when(mLoginRepository.loginRemote(mUser)).thenReturn(Observable.just(mUser));

        mLoginPresenter.login(mUser);
        verify(mLoginRepository).saveUser((User) any());
        verify(mLoginView, never()).showErrorMessage(anyString());
        verify(mLoginView, never()).showNetWorkError();

        verify(mLoginView).hideLoadingDialog();
        verify(mLoginView).startHomeActivity();
        verify(mLoginView).finishActivity();
    }


}
