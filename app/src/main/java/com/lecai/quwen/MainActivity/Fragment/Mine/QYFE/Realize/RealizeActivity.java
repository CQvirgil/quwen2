package com.lecai.quwen.MainActivity.Fragment.Mine.QYFE.Realize;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.lecai.quwen.DaiLog.ExchangeDiaLog;
import com.lecai.quwen.MyView.GridRadioGroup;
import com.lecai.quwen.R;

public class RealizeActivity extends AppCompatActivity {
    private RadioButton radiobtn1,radiobtn2,radiobtn3,radiobtn4,radiobtn5,radiobtn6,radiobtnwx;
    private GridRadioGroup gridRadioGroup;
    private ExchangeDiaLog exchangeDiaLog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realize);
        radiobtn1 = findViewById(R.id.act_realize_radio_btn1);
        radiobtn2 = findViewById(R.id.act_realize_radio_btn2);
        radiobtn3 = findViewById(R.id.act_realize_radio_btn3);
        radiobtn4 = findViewById(R.id.act_realize_radio_btn4);
        radiobtn5 = findViewById(R.id.act_realize_radio_btn5);
        radiobtn6 = findViewById(R.id.act_realize_radio_btn6);
        radiobtnwx = findViewById(R.id.act_realize_radio_btn_wx);
        gridRadioGroup = findViewById(R.id.act_realize_radio_group);
        exchangeDiaLog = new ExchangeDiaLog(this);
        radiobtnwx.setChecked(true);
        radiobtn1.setChecked(true);

    }

    public void Back(View view) {
        finish();
    }

    public void OK(View view) {
        exchangeDiaLog.show();
    }

    public void startRecord(View view) {
        Intent intent = new Intent();
        intent.setAction("startRecordActivity");
        startActivity(intent);
    }
}
