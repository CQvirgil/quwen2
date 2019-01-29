package com.lecai.quwen.MainActivity.Fragment.Task.Assemble.MemberInformation;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ListView;

import com.lecai.quwen.DaiLog.GoAssembleDiaLog;
import com.lecai.quwen.DaiLog.OutAssembleDiaLog;
import com.lecai.quwen.R;

public class MemberInformationActivity extends Activity {
    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_information);
        mListView = findViewById(R.id.act_member_mlistview);
        MemberAdapter adapter = new MemberAdapter(this);
        mListView.setAdapter(adapter);
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
