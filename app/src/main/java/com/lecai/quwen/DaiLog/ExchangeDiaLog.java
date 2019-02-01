package com.lecai.quwen.DaiLog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lecai.quwen.R;

public class ExchangeDiaLog extends BaseDiaLog {
    String edit_text;
    int num;

    public ExchangeDiaLog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_exchange);
        Button btn = findViewById(R.id.dialog_exchange_btn);
        final EditText editText = findViewById(R.id.dialog_exchange_edit_text);
        final ExchangeSuccessDiaLog diaLog = new ExchangeSuccessDiaLog(getContext());
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_text = editText.getText().toString().trim();
                if(!edit_text.equals("")){
                    diaLog.setSuccess(true);
                    diaLog.show();
                }
                else {
                    diaLog.setSuccess(false);
                    diaLog.show();
                }
                dismiss();
            }
        });
    }
}
