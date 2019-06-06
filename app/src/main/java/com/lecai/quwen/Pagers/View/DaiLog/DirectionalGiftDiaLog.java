package com.lecai.quwen.Pagers.View.DaiLog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lecai.quwen.R;

public class DirectionalGiftDiaLog extends BaseDiaLog implements View.OnClickListener {
    public DirectionalGiftDiaLog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_out_assemble);
        TextView textView = findViewById(R.id.dialog_outassemble_text);
        textView.setText("确定赠送吗？");
        Button btn1 = findViewById(R.id.dialog_out_assemble_btn1);
        Button btn2 = findViewById(R.id.dialog_out_assemble_btn2);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dialog_out_assemble_btn1:
                DirectionalGiftSuccessDiaLog diaLog = new DirectionalGiftSuccessDiaLog(getContext());
                diaLog.show();
                dismiss();
                break;
            case R.id.dialog_out_assemble_btn2:
                dismiss();
                break;
        }
    }
}
