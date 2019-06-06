package com.lecai.quwen.Pagers.View.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lecai.quwen.MyApplication;
import com.lecai.quwen.R;
import com.lecai.quwen.wxapi.WXUtil;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        WXUtil.getInstance().regToWx(this, MyApplication.getWxAppId());
    }

    public void Back(View view) {
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        SharedPreferences read = this.getSharedPreferences("Setting", Context.MODE_PRIVATE);
        if(read.getBoolean("haslogin",false)){
            finish();
        }
    }

    public void LoginWX(View view) {
        WXUtil.getInstance().loginToWX();
        finish();
    }
}
