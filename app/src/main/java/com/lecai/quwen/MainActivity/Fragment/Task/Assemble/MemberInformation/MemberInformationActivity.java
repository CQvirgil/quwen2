package com.lecai.quwen.MainActivity.Fragment.Task.Assemble.MemberInformation;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.ListView;

import com.lecai.quwen.DaiLog.GoAssembleDiaLog;
import com.lecai.quwen.DaiLog.OutAssembleDiaLog;
import com.lecai.quwen.R;

import java.util.ArrayList;
import java.util.List;

public class MemberInformationActivity extends Activity {
    private ListView mListView;
    private List<MemberBean> members;
    private MemberAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_information);
        mListView = findViewById(R.id.act_member_mlistview);
        initData();
        adapter = new MemberAdapter(this,members);
        mListView.setAdapter(adapter);
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
                            adapter.addItem(new MemberBean("2","2",2,2));
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

    private void initData(){
        members = new ArrayList<>();
        for(int i=0;i<10;i++){
            members.add(new MemberBean(i+"",i+"",i,i));
        }
    }

    public void Back(View view) {
        finish();
    }

    public void OutAssemble(View view) {
        OutAssembleDiaLog diaLog = new OutAssembleDiaLog(this);
        diaLog.show();
    }

    public void goAssemble(View view) {
        GoAssembleDiaLog diaLog = new GoAssembleDiaLog(this);
        Window window = diaLog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.mydialogstyle);
        diaLog.show();
    }
}
