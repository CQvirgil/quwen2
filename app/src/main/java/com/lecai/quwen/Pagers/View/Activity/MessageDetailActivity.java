package com.lecai.quwen.Pagers.View.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lecai.quwen.Pagers.Model.AndroidRX.RxBus;
import com.lecai.quwen.Pagers.Model.AndroidRX.Rxid;
import com.lecai.quwen.MyApplication;
import com.lecai.quwen.Pagers.Model.NetWork.Client;
import com.lecai.quwen.R;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.functions.Consumer;

public class MessageDetailActivity extends AppCompatActivity implements Consumer<JSONObject> {

    Intent intent;
    String sub_name,sub_unionid,text;
    private boolean isAgree;
    private TextView text_view_title, text_view_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_detail);
        initData();
        initView();
        Log.i("WXEntryActivity_TAG","sub_unionid: "+sub_unionid);
        Log.i("WXEntryActivity_TAG","sub_name: "+MyApplication.getInstance().getUser().getU_unionid());
        RxBus.getInstance().subscribe(JSONObject.class, this);
    }

    private void initData(){
        intent = getIntent();
        sub_name = intent.getStringExtra("sub_name");
        sub_unionid = intent.getStringExtra("sub_unionid");
        text = intent.getStringExtra("text");
    }

    private void initView(){
        text_view_title = findViewById(R.id.act_message_detail_title);
        text_view_content = findViewById(R.id.act_message_detail_content);
        text_view_title.setText(sub_name);
        text_view_content.setText(text);
    }

    public void Back(View view) {
        finish();
    }

    public void accept(View view) throws JSONException {
        isAgree = true;
        postAgree(sub_unionid,sub_name,isAgree);
    }

    public void reject(View view) throws JSONException {
        isAgree = false;
        postAgree(sub_unionid,sub_name,isAgree);
    }

    private void postAgree(String sub_unionid, String sub_name, boolean agree) throws JSONException {
        String url = "http://www.lecaigogo.com:4999/api/v1/disciple/disciple_agree";
        JSONObject json_post = new JSONObject();
        json_post.put("sub_unionid", sub_unionid);
        json_post.put("dom_unionid", MyApplication.getInstance().getUser().getU_unionid());
        json_post.put("agree",agree);
        Client.getInstance().PostServerJ(url,json_post,Rxid.POST_AGRESS);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.getInstance().unSubcribe();
    }

    @Override
    public void accept(JSONObject jsonObject) throws Exception {
        String rxid = jsonObject.getString("Rxid");
        if (rxid == Rxid.POST_AGRESS){
            if(jsonObject.getInt("return_code") == 1 && isAgree){
                Toast.makeText(this, "已接受", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "已拒绝", Toast.LENGTH_SHORT).show();
            }
        }
        Log.i("WXEntryActivity_TAG",jsonObject.toString());
    }
}
