package com.ztq.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import com.ztq.task.CheckWifiThread;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CheckWifiService extends Service {
    private Context mContext;
    private CheckWifiThread mCheckWifiThread;
    private ScheduledExecutorService mExecutorService;

    public CheckWifiService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getBaseContext();
        mCheckWifiThread = new CheckWifiThread(mContext);
        mExecutorService = new ScheduledThreadPoolExecutor(1);
        mExecutorService.scheduleAtFixedRate(mCheckWifiThread, 0, 5, TimeUnit.SECONDS);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
