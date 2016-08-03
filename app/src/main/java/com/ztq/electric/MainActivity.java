package com.ztq.electric;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ztq.service.CheckWifiService;
import com.ztq.utils.Constants;

public class MainActivity extends AppCompatActivity {

    private Button confirmBtn;
    private EditText phoneEt;
    private SharedPreferences mSf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSf = getSharedPreferences(Constants.GLOBAL_SF_NAME, MODE_PRIVATE);
        confirmBtn = (Button) this.findViewById(R.id.confirm_btn);
        phoneEt = (EditText) this.findViewById(R.id.phone_et);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = phoneEt.getText().toString().trim();
                if (!phoneNumber.equals("") && phoneEt.isEnabled()) {
                    SharedPreferences.Editor editor = mSf.edit();
                    editor.putString(Constants.GLOBAL_SF_PHONE, phoneNumber);
                    editor.commit();
                    startCheckService();
                    phoneEt.setEnabled(false);
                } else {
                    if (!phoneEt.isEnabled()) {
                        Toast.makeText(MainActivity.this, "若要重新输入号码请退出app再进入！", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "请输入要通知的手机号！", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void startCheckService() {
        Intent serviceIntent = new Intent(this, CheckWifiService.class);
        startService(serviceIntent);
    }


}
