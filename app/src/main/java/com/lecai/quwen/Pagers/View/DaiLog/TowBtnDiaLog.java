package com.lecai.quwen.Pagers.View.DaiLog;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.lecai.quwen.R;

public class TowBtnDiaLog extends BaseDiaLog {
    private Context context;
    private Button btn_left, btn_right;
    private TextView textView;
    public TowBtnDiaLog(Context context) {
        super(context);
        this.context = context;
    }

    public void setBtnLeftTextColor(int color){
        btn_left.setTextColor(color);
    }

    public void setBtnRightTextColor(int color){
        btn_right.setTextColor(color);
    }

    public void setBtnLeftText(String text){
        btn_left.setText(text);
    }

    public void setBtnRight(String text){
        btn_right.setText(text);
    }

    public void setContentText(String content){
        textView.setText(content);
    }

    private void initView(){
        btn_left = findViewById(R.id.dialog_left_btn);
        btn_right = findViewById(R.id.dialog_right_btn);
        textView = findViewById(R.id.dialog_content);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_tow_btn);
        initView();
    }

}
