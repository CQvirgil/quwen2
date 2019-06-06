package com.lecai.quwen.Pagers.View.DaiLog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lecai.quwen.Pagers.View.Activity.MainActivity;
import com.lecai.quwen.R;

public class GotoReadDiaLog extends BaseDiaLog {
    public GotoReadDiaLog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_goto_read);
        Button btn = findViewById(R.id.dialog_goto_read_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.handler.sendEmptyMessage(2100);
                dismiss();
            }
        });
    }
}
