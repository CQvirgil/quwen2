package com.lecai.quwen.MainActivity.Fragment.Mine.Help;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.lecai.quwen.R;

import java.util.ArrayList;
import java.util.List;

public class HelpActivity extends Activity {
    ListView listView;
    List<HelpBean> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        listView = findViewById(R.id.act_help_listview);
        list = new ArrayList<>();
        initListData();
        HelpListViewAdapter adapter = new HelpListViewAdapter(this,list);
        listView.setAdapter(adapter);
    }

    private void initListData(){
        list.add(new HelpBean("权益份额是什么","权益份额是趣闻APP里的虚拟货币，可以兑换成现金提取"));
        list.add(new HelpBean("如何获得权益份额",""));
        list.add(new HelpBean("什么是工号ID",""));
        list.add(new HelpBean("一个权益份额等于多少现金？",""));
        list.add(new HelpBean("如何邀请朋友？",""));
        list.add(new HelpBean("如何拼团？",""));
        list.add(new HelpBean("如何获取收徒奖励？","每天可以收取5个徒弟，并可获得徒弟每天的基础奖励5%的奖励；一个用户只能设定一个师傅，当天成功收取一个徒弟即完成本项任务"));
    }

    public void Back(View view) {
        finish();
    }
}
