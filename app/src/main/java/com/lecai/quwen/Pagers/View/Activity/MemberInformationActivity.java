package com.lecai.quwen.Pagers.View.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.lecai.quwen.Pagers.Model.AndroidRX.RxBus;
import com.lecai.quwen.Pagers.Model.AndroidRX.Rxid;
import com.lecai.quwen.Pagers.View.DaiLog.GotoAssembleDiaLog;
import com.lecai.quwen.Pagers.View.DaiLog.OutAssembleDiaLog;
import com.lecai.quwen.Pagers.View.Adapter.MemberAdapter;
import com.lecai.quwen.Bean.MemberBean;
import com.lecai.quwen.Pagers.Model.NetWork.Client;
import com.lecai.quwen.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

public class MemberInformationActivity extends Activity implements Consumer {
    private ListView mListView;
    private List<MemberBean> members;
    private MemberAdapter adapter;
    private boolean isEnd = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_information);
        mListView = findViewById(R.id.act_member_mlistview);
        TextView title_id = findViewById(R.id.act_member_title_id);
        members = new ArrayList<>();
        adapter = new MemberAdapter(this);
        mListView.setAdapter(adapter);
        Intent intent = getIntent();
        String teamid = intent.getStringExtra("teamid");
        title_id.setText("团ID:"+teamid);
        RxBus.getInstance().subscribe(String.class,this);
        try {
            getListData(teamid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getListData(String team_id) throws JSONException {
        JSONObject json_post = new JSONObject();
        json_post.put("name_or_tid",team_id);
        String url = "http://www.lecaigogo.com:4999/api/v1/team/team_get";
        Client.getInstance().PostServer(url,json_post,Rxid.GET_TEAM_ALL);
    }

    public void Back(View view) {
        finish();
    }

    public void OutAssemble(View view) {
        OutAssembleDiaLog diaLog = new OutAssembleDiaLog(this);
        diaLog.show();
    }

    public void goAssemble(View view) {
        GotoAssembleDiaLog diaLog = new GotoAssembleDiaLog(this);
        Window window = diaLog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.mydialogstyle);
        diaLog.show();
    }


    private void setmListView(){
        View list_footer = LayoutInflater.from(this).inflate(R.layout.footer_member_information_listview,mListView,false);
        mListView.addFooterView(list_footer);
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    // 当不滚动时
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        // 判断滚动到底部
                        if (mListView.getLastVisiblePosition() == (mListView.getCount() - 1)) {
                            adapter.addItem(new MemberBean("2","2"));
                        }
                        // 判断滚动到顶部
                        if (mListView.getFirstVisiblePosition() == 0) {

                        }
                        break;
                }

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        RxBus.getInstance().unSubcribe();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void accept(Object o) throws Exception {
        String object = (String) o;
        String rxid = object.substring(0,5);
        String data = object.substring(5);
        if(rxid.equals(Rxid.GET_TEAM_ALL)){
            HandListData(data);
            //Log.i("WXEntryActivity_TAG",object);
        }else if(rxid.equals(Rxid.GET_MEMBER_INFO)){
            HandMemberInfo(data);
        }
    }

    private void HandListData(String data) throws JSONException {
        JSONObject json_data = new JSONObject(data);
        members.clear();
        if(json_data.getInt("return_code")==1){
            JSONArray json_members = json_data.getJSONObject("data").getJSONArray("members");
            String m_unionid,m_name;
            String[] m_unionids = new String[json_members.length()];
            Log.i("WXEntryActivity_TAG",json_members.toString());
            for (int i = 0; i<json_members.length(); i++){
                m_unionid = json_members.getJSONObject(i).getString("m_unionid");
                m_name = json_members.getJSONObject(i).getString("m_name");
                //getUser(m_unionid);
                //members.add(new MemberBean(m_name,m_unionid,"","",0));
                m_unionids[i] = m_unionid;
            }

            for(int i = 0; i < m_unionids.length; i++){
                getMemberInfo(m_unionids[i]);
                Log.i("WXEntryActivity_TAG",m_unionids.length+"");
                if (m_unionids.length-1 == i){
                    isEnd = true;
                }
            }
        }
    }

    private void HandMemberInfo(String data) throws JSONException {
        JSONObject json_data = new JSONObject(data);
        Log.i("WXEntryActivity_TAG",data);
        if(json_data.getInt("return_code") == 1){
            JSONObject json_user = json_data.getJSONObject("data");
            String u_unionid = json_user.getString("u_unionid");
            String uid = json_user.getString("uid");
            String name = json_user.getString("name");
            String headimg = json_user.getString("headimg");
            int gold = json_user.getInt("gold");
            members.add(new MemberBean(name,u_unionid,uid,headimg,gold));
            if(isEnd){
                adapter.setList(members);
                //mListView.setAdapter(adapter);
            }
        }
    }


    private void getMemberInfo(String u_unionid) throws JSONException {
        String url = "http://www.lecaigogo.com:4999/api/v1/user/user_info";
        JSONObject json_post = new JSONObject();
        json_post.put("u_unionid", u_unionid);
        Client.getInstance().PostServer(url,json_post,Rxid.GET_MEMBER_INFO);
    }
}
