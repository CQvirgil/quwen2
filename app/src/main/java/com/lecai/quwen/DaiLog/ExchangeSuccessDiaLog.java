package com.lecai.quwen.DaiLog;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.lecai.quwen.R;

public class ExchangeSuccessDiaLog extends BaseDiaLog {
    TextView textView;
    boolean isSuccess = true;


    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public ExchangeSuccessDiaLog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_is_success);
        textView = findViewById(R.id.dialog_is_success_text);
        if(isSuccess){
            textView.setText("申请成功");
        }else{
            textView.setText("申请失败");
        }

    }
}
