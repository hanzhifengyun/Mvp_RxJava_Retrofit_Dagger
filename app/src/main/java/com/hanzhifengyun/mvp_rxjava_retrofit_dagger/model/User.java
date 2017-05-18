package com.hanzhifengyun.mvp_rxjava_retrofit_dagger.model;


import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.util.TextUtil;

public class User{

    private int id;
    private String name;
    private String password;
    private boolean isLogin;


    public int getId() {
        return id;
    }

    public User setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public User setLogin(boolean login) {
        isLogin = login;
        return this;
    }

    public boolean isUserNameEmpty() {
        return TextUtil.isEmpty(name);
    }
}
