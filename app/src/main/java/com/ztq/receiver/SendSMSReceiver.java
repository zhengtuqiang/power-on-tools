package com.ztq.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class SendSMSReceiver extends BroadcastReceiver {
    public SendSMSReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"receiver收到",Toast.LENGTH_SHORT).show();
    }
}
