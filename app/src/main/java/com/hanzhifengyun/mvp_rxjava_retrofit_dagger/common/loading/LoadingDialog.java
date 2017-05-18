package com.hanzhifengyun.mvp_rxjava_retrofit_dagger.common.loading;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.R;


public class LoadingDialog extends Dialog{
    public LoadingDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
    }
}
