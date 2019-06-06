package com.lecai.quwen.Pagers.View.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lecai.quwen.Pagers.View.DaiLog.DirectionalGiftDiaLog;
import com.lecai.quwen.R;

public class DirectionalGiftActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directional_gift);
    }

    public void Back(View view) {
        finish();
    }

    public void Gift(View view) {
        DirectionalGiftDiaLog diaLog = new DirectionalGiftDiaLog(this);
        diaLog.show();
    }
}
