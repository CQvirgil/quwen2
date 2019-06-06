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

public class OutAssembleDiaLog extends BaseDiaLog implements View.OnClickListener,Consumer {
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
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        RxBus.getInstance().subscribe(String.class, this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dialog_out_assemble_btn1:
//                OutAssembleSuccessDiaLog diaLog= new OutAssembleSuccessDiaLog(context);
//                diaLog.show();
//                dismiss();
                try {
                    OutAssemble();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.dialog_out_assemble_btn2:
                dismiss();
                break;
        }

    }

    private void OutAssemble() throws JSONException {
        String url = "http://www.lecaigogo.com:4999/api/v1/team/team_del";
        JSONObject json_post = new JSONObject();
        json_post.put("t_unionid",t_unionid);
        json_post.put("u_unionid",MyApplication.getInstance().getU_unionid());
        Client.getInstance().PostServer(url,json_post,Rxid.DEL_TEAM);
        Log.i("WXEntryActivity_TAG","OutAssemble");
    }

    @Override
    public void accept(Object o) throws Exception {
        String object = (String) o;
        String rxid = object.substring(0,5);
        String data = object.substring(5);
        if(rxid.equals(Rxid.DEL_TEAM)){
            Log.i("WXEntryActivity_TAG",data);
            JSONObject json_data = new JSONObject(data);
            if(json_data.getInt("return_code") == 1){
                Toast.makeText(context, "退出成功", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        }
    }

}
