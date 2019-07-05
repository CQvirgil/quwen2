package com.lecai.quwen.Pagers.View.Activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lecai.quwen.Pagers.View.DaiLog.SetSecurityPassWordDialog;
import com.lecai.quwen.Pagers.View.DaiLog.TowBtnDiaLog;
import com.lecai.quwen.R;

public class GiftActivity extends ToolBarActivity implements View.OnClickListener {
    private Button btn_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift);
        setToolBar("定向赠送", "");
        setToolbarColor(Color.WHITE);
        btn_confirm = findViewById(R.id.act_gift_btn_confirm);
        btn_confirm.setOnClickListener(this);
    }

    public void OnClickConfirm(View view) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dialog_left_btn:
                break;
            case R.id.dialog_right_btn:
                SetSecurityPassWordDialog dialog = new SetSecurityPassWordDialog(this);
                dialog.show();
                break;
            case R.id.act_gift_btn_confirm:
                TowBtnDiaLog diaLog = new TowBtnDiaLog(this, "还没设置安全密码哦", "直接赠送", "去设置", this, this);
                diaLog.show();
                break;
        }
    }
}
