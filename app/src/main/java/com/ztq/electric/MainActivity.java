package com.ztq.electric;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ztq.service.CheckWifiService;
import com.ztq.task.CheckWifiThread;

import java.util.concurrent.ScheduledExecutorService;

public class MainActivity extends AppCompatActivity {

    private CheckWifiThread mThread;
    private ScheduledExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /* mThread = new CheckWifiThread(this);
        //mThread.start();
        executorService = new ScheduledThreadPoolExecutor(1);
        executorService.scheduleAtFixedRate(mThread,0,10, TimeUnit.SECONDS);*/
        Intent serviceIntent = new Intent(this, CheckWifiService.class);
        startService(serviceIntent);
    }
}
