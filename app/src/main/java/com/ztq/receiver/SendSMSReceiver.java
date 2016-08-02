package com.ztq.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.SmsManager;
import android.widget.Toast;

import com.ztq.service.CheckWifiService;
import com.ztq.utils.Constants;

import java.util.List;

public class SendSMSReceiver extends BroadcastReceiver {
    private Context mContext;
    private SharedPreferences mSf;

    public SendSMSReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context;
        mSf = context.getSharedPreferences(Constants.GLOBAL_SF_NAME, Context.MODE_PRIVATE);
        String phoneNumber = mSf.getString(Constants.GLOBAL_SF_PHONE, "");
        Toast.makeText(context, "有电了！即将发信息到->" + phoneNumber, Toast.LENGTH_SHORT).show();
        sendSMS(phoneNumber, "有电了！");
        CheckWifiService.shutDownExecutor();
    }

    /**
     * 直接发送短信
     *
     * @param phoneNumber 电话号码
     * @param message     信息内容
     */
    private void sendSMS(String phoneNumber, String message) {

        //获取短信管理器
        SmsManager smsManager = SmsManager.getDefault();
        //拆分短信内容（手机短信长度限制）
        List<String> divideContents = smsManager.divideMessage(message);
        for (String text : divideContents) {
            smsManager.sendTextMessage(phoneNumber, null, text, null, null);
        }
    }
}
