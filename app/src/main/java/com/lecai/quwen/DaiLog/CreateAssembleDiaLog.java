package com.lecai.quwen.DaiLog;

import android.content.Context;
import android.os.Bundle;
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

public class CreateAssembleDiaLog extends BaseDiaLog implements Consumer {
    private EditText editText;
    private TextView textView;
    private Button button;
    public CreateAssembleDiaLog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_create_assemble);
        initView();
        RxBus.getInstance().subscribe(String.class,this);
        setButton();
    }

    private void initView(){
        editText = findViewById(R.id.dialog_create_assemble_edit_text);
        textView = findViewById(R.id.dialog_create_assemble_text_err);
        button = findViewById(R.id.dialog_create_assemble_btn);
    }

    private void setButton(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.lecaigogo.com:4999/api/v1/team/team_create";
                String team_name = editText.getText().toString();
                JSONObject json_post = new JSONObject();
                try {
                    json_post.put("u_unionid",MyApplication.getInstance().getUser().getU_unionid());
                    json_post.put("t_name",team_name);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Client.getInstance().PostServer(url,json_post,Rxid.CREATE_TEAM);
            }
        });
    }

    @Override
    public void accept(Object o) throws Exception {
        String object = (String) o;
        String rxid = object.substring(0,5);
        String data = object.substring(5);
        if(rxid.equals(Rxid.CREATE_TEAM)){
            HandData(data);
        }
    }

    private void HandData(String data) throws JSONException {
        dismiss();
        JSONObject json_data = new JSONObject(data);
        if(json_data.getInt("return_code")==1){
            String url = "http://www.lecaigogo.com:4999/api/v1/team/team_list";
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("u_unionid", MyApplication.getInstance().getUser().getU_unionid());
            Client.getInstance().PostServer(url,jsonObject,Rxid.GET_TEAM_LIST);
        }
    }
}
