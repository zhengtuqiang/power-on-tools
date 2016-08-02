package com.ztq.task;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * 检查wifi是否连接的线程
 * Created by ztq on 2016-08-02 16:36.
 */
public class CheckWifiThread extends Thread {
    private Context mContext;

    public CheckWifiThread(Context context) {
        mContext = context;
    }

    @Override
    public void run() {
        super.run();
        if (isWifiConnected()) {
            ((Activity) mContext).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(mContext, "连接到wifi", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            ((Activity) mContext).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(mContext, "没有连接到wifi", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /**
     * 判断是否连上了wifi网络
     * 局限：不能判断是否wifi可以上网
     *
     * @return true即当前已连接到wifi；false即没有连接到wifi
     */
    private boolean isWifiConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (networkInfo.isConnected() && networkInfo.isAvailable()) {
            return true;
        }
        return false;
    }
}
