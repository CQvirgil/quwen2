package com.lecai.quwen.Pagers.View.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lecai.quwen.Pagers.View.DaiLog.ExchangeDiaLog;
import com.lecai.quwen.Pagers.View.DaiLog.TowBtnDiaLog;
import com.lecai.quwen.R;

public class ExchangeActivity extends ToolBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);
        setToolBar("竞价变现", "竞价记录");
        setToolbarColor(Color.WHITE);
        settoolbarTitleColor(Color.BLACK);
        setToolbar_rightOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExchangeActivity.this, JJRecordActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onClickApplyBtn(View view) {
        ExchangeDiaLog diaLog = new ExchangeDiaLog(this);
        diaLog.show();
    }
}
