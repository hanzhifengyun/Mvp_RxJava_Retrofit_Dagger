package com.hanzhifengyun.mvp_rxjava_retrofit_dagger.home;

import android.content.Context;
import android.content.Intent;

import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.R;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.base.BaseActivity;



public class HomeActivity extends BaseActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, HomeActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initEvent() {

    }
}
