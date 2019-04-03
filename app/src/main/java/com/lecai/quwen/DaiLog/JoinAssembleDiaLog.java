package com.lecai.quwen.DaiLog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lecai.quwen.AndroidRX.RxBus;
import com.lecai.quwen.AndroidRX.Rxid;
import com.lecai.quwen.MyApplication;
import com.lecai.quwen.NetWork.Client;
import com.lecai.quwen.R;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.functions.Consumer;

public class JoinAssembleDiaLog extends BaseDiaLog implements Consumer {
    private EditText editText;
    private Button btn;
    private TextView text_error,text_id;

    public JoinAssembleDiaLog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_join_assemble);
        initView();
        RxBus.getInstance().subscribe(String.class,this);
    }
    private void initView(){
        editText = findViewById(R.id.dialog_join_assemble_edit_text);
        btn = findViewById(R.id.dialog_join_assemble_btn);
        text_error = findViewById(R.id.dialog_join_assemble_text_error);
        text_id = findViewById(R.id.dialog_join_assemble_text_id);
        setBtn();
    }

    private void setBtn(){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.lecaigogo.com:4999/api/v1/team/team_get";
                String str =editText.getText().toString();
                JSONObject json_post = new JSONObject();
                try {
                    json_post.put("name_or_tid",str);
                    Client.getInstance().PostServer(url,json_post,Rxid.GET_TEAM);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void accept(Object o) throws Exception {
        String object = (String) o;
        String rxid = object.substring(0,5);
        String data = object.substring(5);
        if(rxid.equals(Rxid.GET_TEAM)){
            HandData(data);
        }
        if(rxid.equals(Rxid.ADD_TEAM)){
            HandAddTeam(data);
        }
    }

    private void HandAddTeam(String data) throws JSONException {
        JSONObject json_data = new JSONObject(data);
        if(json_data.getInt("return_code") == 1){
            String url = "http://www.lecaigogo.com:4999/api/v1/team/team_list";
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("u_unionid", MyApplication.getInstance().getUser().getU_unionid());
            Client.getInstance().PostServer(url,jsonObject,Rxid.GET_TEAM_LIST);
            dismiss();
        }
        Log.i("WXEntryActivity_TAG",json_data.toString());
    }

    private void HandData(String data) throws JSONException {
        JSONObject json_data = new JSONObject(data);
        if(json_data.getInt("return_code")==20401){
            text_error.setText("*团id错误请重新输入");
        }else if(json_data.getInt("return_code")==1){
            String t_unionid = json_data.getJSONObject("data").getString("t_unionid");
            String url = "http://www.lecaigogo.com:4999/api/v1/team/team_add";
            JSONObject json_post = new JSONObject();
            json_post.put("u_unionid",MyApplication.getInstance().getUser().getU_unionid());
            json_post.put("t_unionid",t_unionid);
            Client.getInstance().PostServer(url,json_post,Rxid.ADD_TEAM);
        }
    }
}
