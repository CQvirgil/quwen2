package com.lecai.quwen.Pagers.View.DaiLog;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.lecai.quwen.R;

public class DirectionalGiftSuccessDiaLog extends BaseDiaLog {
    public DirectionalGiftSuccessDiaLog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_out_assemble_success);
        TextView textview = findViewById(R.id.dialog_success_text);
        textview.setText("赠送成功");
    }
}
