package com.lecai.quwen.Pagers.View.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

import com.lecai.quwen.Pagers.View.DaiLog.GotoAssembleDiaLog;
import com.lecai.quwen.Pagers.View.DaiLog.TowBtnDiaLog;
import com.lecai.quwen.Pagers.View.Adapter.MemberAdapter;
import com.lecai.quwen.Bean.MemberBean;
import com.lecai.quwen.R;

import java.util.ArrayList;
import java.util.List;

public class MemberInformationActivity extends Activity{
    private ListView mListView;
    private List<MemberBean> members;
    private MemberAdapter adapter;
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
    }

    public void Back(View view) {
        finish();
    }

    public void OutAssemble(View view) {
        TowBtnDiaLog diaLog = new TowBtnDiaLog(this);
        diaLog.show();
    }

    public void goAssemble(View view) {
        GotoAssembleDiaLog diaLog = new GotoAssembleDiaLog(this);
        Window window = diaLog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.mydialogstyle);
        diaLog.show();
    }

}
