package com.lecai.quwen.Pagers.View.DaiLog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lecai.quwen.R;

public class TowBtnDiaLog extends BaseDiaLog {
    private Context context;
    private Button btn_left, btn_right;
    private TextView textView;
    private String content,str_btn_left,str_btn_right;
    private View.OnClickListener onLeftClick, onRightClick;

    public TowBtnDiaLog(Context context, String content, String str_btn_left, String str_btn_right, View.OnClickListener onLeftClick, View.OnClickListener onRightClick) {
        super(context);
        this.content = content;
        this.str_btn_left = str_btn_left;
        this.str_btn_right = str_btn_right;
        this.onLeftClick = onLeftClick;
        this.onRightClick = onRightClick;
    }

    public TowBtnDiaLog(Context context, String content, String str_btn_left, String str_btn_right) {
        super(context);
        this.content = content;
        this.str_btn_left = str_btn_left;
        this.str_btn_right = str_btn_right;
    }

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
        textView.setText(content);
        btn_left.setText(str_btn_left);
        btn_right.setText(str_btn_right);
        btn_left.setOnClickListener(onLeftClick);
        btn_right.setOnClickListener(onRightClick);
    }

    public void setbtnLeftOnClickListener(View.OnClickListener onClickListener){
        btn_left.setOnClickListener(onClickListener);
    }

    public void setBtnRightOnClickListener(View.OnClickListener onClickListener){
        btn_right.setOnClickListener(onClickListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_tow_btn);
        initView();
    }

}
