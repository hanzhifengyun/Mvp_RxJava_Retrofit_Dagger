package com.hanzhifengyun.mvp_rxjava_retrofit_dagger.common.http;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;

import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.R;
import com.hanzhifengyun.mvp_rxjava_retrofit_dagger.di.ActivityScope;

import javax.inject.Inject;

@ActivityScope
public class HttpManager implements IHttpManager {

    private Context mContext;

    @Inject
    public HttpManager(Activity context) {
        this.mContext = context;
    }

    private static final String TAG = "HttpManager";

    @Override
    public void showNetworkFailureDialog() {
        new AlertDialog.Builder(mContext)
                .setMessage(R.string.network_failure)
                .setPositiveButton(R.string.go_setting, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss(); //关闭dialog
                        openSystemNetworkSetting();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }

    private void openSystemNetworkSetting() {
        Intent intent = new Intent(Settings.ACTION_SETTINGS);
        mContext.startActivity(intent);
    }


    /**
     * 判断是否连接网络
     */
    @Override
    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager)
                mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }


}
