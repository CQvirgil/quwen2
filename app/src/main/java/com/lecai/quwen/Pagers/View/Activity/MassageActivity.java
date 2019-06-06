package com.lecai.quwen.Pagers.View.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lecai.quwen.Pagers.Model.AndroidRX.RxBus;
import com.lecai.quwen.Pagers.Model.AndroidRX.Rxid;
import com.lecai.quwen.Bean.Message;
import com.lecai.quwen.Pagers.View.Adapter.MassageAdapter;
import com.lecai.quwen.MyApplication;
import com.lecai.quwen.Pagers.Model.NetWork.Client;
import com.lecai.quwen.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

public class MassageActivity extends AppCompatActivity implements Consumer<JSONObject> {
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_massage);
        listView = findViewById(R.id.act_mag_listview);
        RxBus.getInstance().subscribe(JSONObject.class,this);
        try {
            getMessage();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setListView(final List<Message> list){
        MassageAdapter adapter = new MassageAdapter(this,list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("sub_name",list.get(position).getSub_name());
                intent.putExtra("sub_unionid",list.get(position).getSub_unionid());
                intent.putExtra("text",list.get(position).getText());
                intent.setAction("startMessageDetailActivity");
                startActivity(intent);
            }
        });
    }

    private void getMessage() throws JSONException {
        String url = "http://www.lecaigogo.com:4999/api/v1/news/disciple_news";
        JSONObject json_post = new JSONObject();
        json_post.put("u_unionid",MyApplication.getInstance().getU_unionid());
        Client.getInstance().PostServerJ(url,json_post,Rxid.GET_MESSAGE);
    }

    public void Back(View view) {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.getInstance().unSubcribe();
    }

    @Override
    public void accept(JSONObject jsonObject) throws Exception {
        String rxid = jsonObject.getString("Rxid");
        Log.i("WXEntryActivity_TAG",jsonObject.toString());
        if(rxid == Rxid.GET_MESSAGE && jsonObject.getInt("return_code") == 1){
            JSONObject json_data = jsonObject.getJSONObject("data");
            JSONArray json_news_list = json_data.getJSONArray("news_list");
            List<Message> list = new ArrayList<>();
            list.clear();
            for(int i=0; i<json_news_list.length(); i++){
                JSONObject msg = json_news_list.getJSONObject(i);
                String sub_unionid = msg.getString("sub_unionid");
                String sub_name = msg.getString("sub_name");
                String text = msg.getString("text");
                Message message = new Message(sub_unionid,sub_name,text);
                list.add(message);
            }
            setListView(list);
        }
    }
}
