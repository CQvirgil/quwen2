package com.lecai.quwen.MainActivity.Fragment.Task.Mentor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

import com.lecai.quwen.AndroidRX.RxBus;
import com.lecai.quwen.AndroidRX.Rxid;
import com.lecai.quwen.Bean.Apprentice;
import com.lecai.quwen.DaiLog.GotoAssembleDiaLog;
import com.lecai.quwen.MyApplication;
import com.lecai.quwen.MyView.CircleImage;
import com.lecai.quwen.NetWork.Client;
import com.lecai.quwen.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

public class MentorActivity extends AppCompatActivity implements Consumer {
    private ListView listView;
    private CircleImage headimg;
    private TextView mentor_name,mentor_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor);
        initView();
        RxBus.getInstance().subscribe(String.class,this);
        try {
            getMasterList(MyApplication.getInstance().getUser().getU_unionid());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initView(){
        mentor_name = findViewById(R.id.act_mentor_name);
        mentor_id = findViewById(R.id.act_mentor_id);
        listView = findViewById(R.id.act_mentor_list_view);
    }

    public void Back(View view) {
        finish();
    }

    public void startBindingMasterActivity(View view) {
        Intent intent = new Intent();
        intent.setAction("startBindingMasterActivity");
        startActivity(intent);
    }

    public void GetApprentice(View view) {
        GotoAssembleDiaLog diaLog = new GotoAssembleDiaLog(this);
        Window window = diaLog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.mydialogstyle);
        diaLog.show();
    }

    public void addApprentice(View view) {
        Intent intent = new Intent();
        intent.setAction("startBindingMasterActivity");
        intent.putExtra("addApprentice",1);
        startActivity(intent);
    }

    private void getMasterList(String u_unionid) throws JSONException {
        String url = "http://www.lecaigogo.com:4999/api/v1/disciple/disciple_list";
        JSONObject json_post = new JSONObject();
        json_post.put("u_unionid",u_unionid);
        Client.getInstance().PostServer(url,json_post,Rxid.GET_MASTER_LIST);
        Log.i("WXEntryActivity_TAG",u_unionid);
    }

    @Override
    public void accept(Object o) throws Exception {
        String object = (String) o;
        String rxid = object.substring(0,5);
        String data = object.substring(5);
        if(rxid.equals(Rxid.GET_MASTER_LIST)){
            JSONObject json_data = new JSONObject(data);
            int return_code = json_data.getInt("return_code");
            if(return_code == 1){
                JSONObject json_list = json_data.getJSONObject("data");
                JSONArray sub_list = json_list.getJSONArray("sub_list");
                List<Apprentice> list = new ArrayList<>();
                for (int i = 0; i < sub_list.length(); i++){
                    JSONObject json_item = sub_list.getJSONObject(i);
                    String sub_unionid = json_item.getString("sub_unionid");
                    String uid = json_item.getString("uid");
                    String name = json_item.getString("name");
                    int gold = json_item.getInt("gold");
                    list.add(new Apprentice(sub_unionid, uid, name, gold));
                }

//                JSONObject dom_info = json_list.getJSONObject("dom_info");
//                String dom_name = dom_info.getString("name");
//                String uid = dom_info.getString("uid");
//                mentor_name.setText(dom_name);
//                mentor_id.setText("ID:"+uid);
                steListView(list);
                Log.i("WXEntryActivity_TAG",json_list.toString());
            }

        }
    }

    private void steListView(List<Apprentice> list){
        MentorAdapter adapter = new MentorAdapter(this,list);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.getInstance().unSubcribe();
    }
}
