package com.lecai.quwen.Pagers.View.DaiLog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lecai.quwen.R;

public class ExchangeDiaLog extends BaseDiaLog implements View.OnClickListener {
    private ImageView close;
    private LinearLayout select_pay;
    public ExchangeDiaLog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_exchange);
        close = findViewById(R.id.dialog_exchange_close);
        close.setOnClickListener(this);
        select_pay = findViewById(R.id.dialog_exchange_select_pay);
        select_pay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dialog_exchange_close:
                dismiss();
                break;
            case R.id.dialog_exchange_select_pay:
                SelectPayDialog selectPayDialog = new SelectPayDialog(getContext());
                selectPayDialog.show();
                break;
        }
    }
}
