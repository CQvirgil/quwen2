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
        list.add(new HelpBean("如何获得权益份额","①阅读首页资讯，每阅读5分钟即可获得1个权益份额，上不封顶。\n" +
                "②完成日常任务可获得相应的权益份额，全部日常任务完成后，还可另外获得5个权益份额。\n" +
                "③完成成长任务可获得相应的权益份额。"));
        list.add(new HelpBean("什么是工号ID","工号ID是趣闻APP用户的唯一身份识别码，用户可以通过搜索工号ID来寻找用户。"));
        list.add(new HelpBean("一个权益份额等于多少现金？","1000个权益份额约等于1元（根据实际情况而定）。用户如果想要将权益份额变现，可以根据官方给出的参考价格衡量\n" +
                "并出售自己的权益份额，同样的变现金额，出售的权益份额数量越多，越有可能交易成功，否则也可能失败。"));
        list.add(new HelpBean("如何邀请朋友？","用户可以在趣闻APP任务页面→日常任务→去拼团→点击你想要邀请朋友入的团→点击右上角”去拼团“即可。"));
        list.add(new HelpBean("如何拼团？","用户可以在趣闻APP任务页面→日常任务→去收徒→点击“我的师傅”绑定按钮→搜索用户ID绑定即可。"));
        list.add(new HelpBean("如何获取收徒奖励？","每天可以收取5个徒弟，并可获得徒弟每天的基础奖励5%的奖励；一个用户只能设定一个师傅，当天成功收取一个徒弟即完成本项任务"));
    }

    public void Back(View view) {
        finish();
    }
}
