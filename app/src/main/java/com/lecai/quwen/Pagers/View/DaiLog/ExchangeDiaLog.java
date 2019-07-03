package com.lecai.quwen.Pagers.View.DaiLog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.lecai.quwen.R;

public class ExchangeDiaLog extends BaseDiaLog implements View.OnClickListener {
    private ImageView close;
    public ExchangeDiaLog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_exchange);
        close = findViewById(R.id.dialog_exchange_close);
        close.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dialog_exchange_close:
                dismiss();
                break;
        }
    }
}
