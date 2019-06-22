package com.lecai.quwen.Pagers.View.DaiLog;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lecai.quwen.Pagers.Model.AndroidRX.RxBus;
import com.lecai.quwen.Pagers.Model.AndroidRX.Rxid;
import com.lecai.quwen.MyApplication;
import com.lecai.quwen.Pagers.Model.NetWork.Client;
import com.lecai.quwen.R;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.functions.Consumer;

public class OutAssembleDiaLog extends BaseDiaLog {
    private Context context;
    private String t_unionid;
    private String u_unionid;
    public OutAssembleDiaLog(Context context) {
        super(context);
        this.context = context;
    }

    public String getT_unionid() {
        return t_unionid;
    }

    public void setT_unionid(String t_unionid) {
        this.t_unionid = t_unionid;
    }

    public String getU_unionid() {
        return u_unionid;
    }

    public void setU_unionid(String u_unionid) {
        this.u_unionid = u_unionid;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_out_assemble);
        Button btn1 = findViewById(R.id.dialog_out_assemble_btn1);
        Button btn2 = findViewById(R.id.dialog_out_assemble_btn2);
    }

}
