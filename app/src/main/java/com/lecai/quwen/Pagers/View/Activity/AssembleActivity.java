package com.lecai.quwen.Pagers.View.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lecai.quwen.Pagers.Model.AndroidRX.RxBus;
import com.lecai.quwen.Pagers.Model.AndroidRX.Rxid;
import com.lecai.quwen.Bean.Team;
import com.lecai.quwen.Pagers.View.DaiLog.CreateAssembleDiaLog;
import com.lecai.quwen.Pagers.View.DaiLog.JoinAssembleDiaLog;
import com.lecai.quwen.Pagers.View.DaiLog.OutAssembleDiaLog;
import com.lecai.quwen.Pagers.View.Adapter.AssembleAdapter;
import com.lecai.quwen.MyApplication;
import com.lecai.quwen.MyView.mGridView;
import com.lecai.quwen.Pagers.Model.NetWork.Client;
import com.lecai.quwen.Pagers.Model.NetWork.Data.Data;
import com.lecai.quwen.Pagers.Model.NetWork.HttpRequest;
import com.lecai.quwen.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

public class AssembleActivity extends ToolBarActivity implements Consumer {
    private mGridView mGridView1, mGridView2;
    private CreateAssembleDiaLog diaLog1;
    private JoinAssembleDiaLog diaLog2;
    private List<Team> sub_team, dom_team;
    public static Handler handler;
    private AssembleAdapter adapter_sub_team, adapter_dom_team;
    private RelativeLayout is_sub_team_none, is_dom_team_none;
    private static int SET_SUB_TEAM = 2001,
            SET_DOM_TEAM = 2002,
            SET_SUB_TEAM_NONE = 2003,
            SET_SUB_TEAM_HAVE = 2004,
            SET_DOM_TEAM_NONE = 2005,
            SET_DOM_TEAM_HAVE = 2006;

    private void setToolBar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView toolbar_title = findViewById(R.id.toolbar_title);
        toolbar_title.setText("拼团");
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assemble);
        setToolBar();
        mGridView1 = findViewById(R.id.act_assemble_mgridview1);
        mGridView2 = findViewById(R.id.act_assemble_mgridview2);
        is_sub_team_none = findViewById(R.id.act_assemble_listnone2);
        is_dom_team_none = findViewById(R.id.act_assemble_listnone);
        diaLog2 = new JoinAssembleDiaLog(this);
        diaLog1 = new CreateAssembleDiaLog(this);
        adapter_sub_team = new AssembleAdapter(this);
        adapter_dom_team = new AssembleAdapter(this);
        final OutAssembleDiaLog diaLog = new OutAssembleDiaLog(this);
        initHandler();
        sub_team = new ArrayList<>();
        dom_team = new ArrayList<>();
        RxBus.getInstance().subscribe(String.class, this);
        mGridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setAction("startMemberInformationActivity");
                intent.putExtra("teamid", dom_team.get(position).getTid());
                startActivity(intent);
            }
        });
        mGridView1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                diaLog.setT_unionid(dom_team.get(position).getT_unionid());
                diaLog.show();
                return true;
            }
        });
        mGridView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setAction("startMemberInformationActivity");
                intent.putExtra("teamid", sub_team.get(position).getTid());
                startActivity(intent);
            }
        });
        try {
            if (Data.getInstance().getDom_team() == null || Data.getInstance().getSub_team() == null) {
                HttpRequest.getInstance().getTeamList();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("HandlerLeak")
    private void initHandler() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 2001:
                        adapter_sub_team.setList(Data.getInstance().getSub_team());
                        mGridView2.setAdapter(adapter_sub_team);
                        break;
                    case 2002:
                        adapter_dom_team.setList(Data.getInstance().getDom_team());
                        mGridView1.setAdapter(adapter_dom_team);
                        break;
                    case 2003:
                        is_sub_team_none.setVisibility(View.VISIBLE);
                        break;
                    case 2004:
                        is_sub_team_none.setVisibility(View.INVISIBLE);
                        break;
                    case 2005:
                        is_dom_team_none.setVisibility(View.VISIBLE);
                        break;
                    case 2006:
                        is_dom_team_none.setVisibility(View.INVISIBLE);
                        break;
                }

            }
        };
    }

    private void getTeamList() throws JSONException {
        String url = "http://www.lecaigogo.com:4999/api/v1/team/team_list";
        JSONObject jsonObject = new JSONObject();
        if (MyApplication.getInstance() != null) {
            jsonObject.put("u_unionid", MyApplication.getInstance().getU_unionid());
        }
        Client.getInstance().PostServer(url, jsonObject, Rxid.GET_TEAM_LIST);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Data.getInstance().getSub_team() != null) {
            handler.sendEmptyMessage(2001);
            handler.sendEmptyMessage(2004);
        } else {
            handler.sendEmptyMessage(2003);
        }
        if (Data.getInstance().getDom_team() != null) {
            handler.sendEmptyMessage(2002);
            handler.sendEmptyMessage(2006);
        } else {
            handler.sendEmptyMessage(2005);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        int show_dialog = intent.getIntExtra("show_dialog", 0);
        switch (show_dialog) {
            case 1:
                diaLog1.show();
                break;
            case 2:
                diaLog2.show();
                break;
        }

    }

    public void Back(View view) {
        finish();
    }

    public void CreateAssemble(View view) {
        diaLog1.show();

    }

    @Override
    protected void onPause() {
        super.onPause();
        RxBus.getInstance().unSubcribe();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.getInstance().unSubcribe();
    }

    public void JoinAssemble(View view) {
        diaLog2.show();
    }

    @Override
    public void accept(Object o) throws Exception {
        String object = (String) o;
        String rxid = object.substring(0, 5);
        String data = object.substring(5);
        if (rxid.equals(Rxid.GET_TEAM_LIST)) {
            HandListData(data);
            Log.i("WXEntryActivity_TAG", data);
        } else if (rxid.equals(Rxid.DEL_TEAM)) {
            getTeamList();
        }
    }

    private void HandListData(String data) throws JSONException {
        JSONObject json_teamlist = new JSONObject(data);
        if (json_teamlist.getInt("return_code") == 1) {
            JSONObject json_teamlist_data = json_teamlist.getJSONObject("data");
            if (json_teamlist_data.getInt("sub_count") > 0) {
                handler.sendEmptyMessage(2004);
                sub_team.clear();
                for (int i = 0; i < json_teamlist_data.getJSONArray("sub_team").length(); i++) {
                    JSONObject json_team = (JSONObject) json_teamlist_data.getJSONArray("sub_team").get(i);
                    String t_unionid = json_team.getString("t_unionid");
                    String name = json_team.getString("name");
                    String tid = json_team.getString("tid");
                    int number = json_team.getInt("number");
                    sub_team.add(new Team(t_unionid, name, tid, number));
                }
                handler.sendEmptyMessage(2001);
            } else {
                handler.sendEmptyMessage(2003);
            }
            if (json_teamlist_data.getInt("dom_count") >= 0) {
                handler.sendEmptyMessage(2006);
                dom_team.clear();
                for (int i = 0; i < json_teamlist_data.getJSONArray("dom_team").length(); i++) {
                    JSONObject json_team = (JSONObject) json_teamlist_data.getJSONArray("dom_team").get(i);
                    String t_unionid = json_team.getString("t_unionid");
                    String name = json_team.getString("name");
                    String tid = json_team.getString("tid");
                    int number = json_team.getInt("number");
                    dom_team.add(new Team(t_unionid, name, tid, number));
                }
                handler.sendEmptyMessage(2002);
                if (json_teamlist_data.getInt("dom_count") == 0) {
                    handler.sendEmptyMessage(2005);
                }
            }
        }
    }
}
