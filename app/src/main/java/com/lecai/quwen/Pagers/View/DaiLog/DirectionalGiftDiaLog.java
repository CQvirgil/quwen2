package com.lecai.quwen.Pagers.View.DaiLog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.lecai.quwen.R;

public class DirectionalGiftDiaLog extends BaseDiaLog implements View.OnClickListener {
    public DirectionalGiftDiaLog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_tow_btn);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }
}
