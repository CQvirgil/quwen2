package com.lecai.quwen.DaiLog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lecai.quwen.R;

public class OutAssembleDiaLog extends BaseDiaLog implements View.OnClickListener {
    private Context context;
    public OutAssembleDiaLog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_out_assemble);
        Button btn1 = findViewById(R.id.dialog_out_assemble_btn1);
        Button btn2 = findViewById(R.id.dialog_out_assemble_btn2);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dialog_out_assemble_btn1:
                OutAssembleSuccessDiaLog diaLog= new OutAssembleSuccessDiaLog(context);
                diaLog.show();
                dismiss();
                break;
            case R.id.dialog_out_assemble_btn2:
                dismiss();
                break;
        }

    }
}
