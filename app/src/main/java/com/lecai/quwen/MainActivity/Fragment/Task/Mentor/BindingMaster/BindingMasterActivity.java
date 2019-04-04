package com.lecai.quwen.MainActivity.Fragment.Task.Mentor.BindingMaster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lecai.quwen.AndroidRX.RxBus;
import com.lecai.quwen.AndroidRX.Rxid;
import com.lecai.quwen.MyApplication;
import com.lecai.quwen.NetWork.Client;
import com.lecai.quwen.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

public class BindingMasterActivity extends AppCompatActivity implements Consumer {
    private ListView listView;
    private EditText editText;
    private Button btn_search;
    private BindingMasterAdapter adapter;
    private  List<MasterBean> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binding_master);
        RxBus.getInstance().subscribe(String.class,this);
        listView = findViewById(R.id.act_binding_master_listview);
        TextView title = findViewById(R.id.act_binding_master_title);
        List<MasterBean> list = new ArrayList<>();
        adapter = new BindingMasterAdapter(this);
        adapter.setList(list);
        if(getIntent().getIntExtra("addApprentice",-1) == 1){
            title.setText("添加");
            adapter.setIsadd(true);
            listView.setAdapter(adapter);
        }else{
            adapter.setIsadd(false);
            listView.setAdapter(adapter);
        }
        initView();
        setBtn_search();
    }

    private void initView(){
        editText = findViewById(R.id.act_binding_master_edittext);
        btn_search = findViewById(R.id.act_binding_master_btn_search);
    }



    private void setBtn_search(){
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString();
                try {
                    SearchMaster(MyApplication.getInstance().getU_unionid(),name);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void Back(View view) {
        finish();
    }

    private void SearchMaster(String sub_unionid,String name_or_did) throws JSONException {
        String url = "http://www.lecaigogo.com:4999/api/v1/disciple/disciple_search";
        JSONObject json_post = new JSONObject();
        json_post.put("sub_unionid",sub_unionid);
        json_post.put("name_or_did",name_or_did);
        Client.getInstance().PostServer(url,json_post,Rxid.SEARCH_MASTER);
    }

    @Override
    public void accept(Object o) throws Exception {
        String object = (String) o;
        String rxid = object.substring(0,5);
        String data = object.substring(5);
        if(rxid.equals(Rxid.SEARCH_MASTER)){
            JSONObject json_data = new JSONObject(data);
            if(json_data.getInt("return_code") == 1){
                JSONObject json_user = json_data.getJSONObject("data");
                String uid = json_user.getString("uid");
                String name = json_user.getString("name");
                String u_unionid = json_user.getString("u_unionid");
                int  is_follow = json_user.getInt("is_follow");
                MasterBean master = new MasterBean(uid,name,u_unionid,is_follow);
                list = new ArrayList<>();
                list.add(master);
                adapter.setList(list);
                adapter.notifyDataSetChanged();
                Log.i("WXEntryActivity_TAG",master.getUid());
                getHeadImage(u_unionid);
            }
        }else if(rxid.equals(Rxid.BINDING_MASTER)){
            JSONObject json_data = new JSONObject(data);
            int return_code = json_data.getInt("return_code");
            if(return_code == 1){
                Toast.makeText(this, "已发送信息对方接受后成为你的师傅或徒弟", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "发送失败", Toast.LENGTH_SHORT).show();
            }
        }else if(rxid.equals(Rxid.GET_HEAD_IMAGE)){
            Log.i("WXEntryActivity_TAG",data);
            JSONObject json_data = new JSONObject(data);
            if(json_data.getInt("return_code") == 1){
                String headimg = json_data.getString("headimg");
                list.get(0).setHeadimg_url(headimg);
                adapter.setList(list);
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void getHeadImage(String u_unionid) throws JSONException {
        String url = "http://www.lecaigogo.com:4999/api/v1/user/user_img";
        JSONObject json_post = new JSONObject();
        json_post.put("u_unionid",u_unionid);
        Client.getInstance().PostServer(url,json_post,Rxid.GET_HEAD_IMAGE);
    }
}
