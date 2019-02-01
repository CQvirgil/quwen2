package com.lecai.quwen.MainActivity.Fragment.Task.Mentor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ListView;

import com.lecai.quwen.DaiLog.GotoAssembleDiaLog;
import com.lecai.quwen.MyView.CircleImage;
import com.lecai.quwen.R;

public class MentorActivity extends AppCompatActivity {
    private ListView listView;
    private CircleImage headimg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor);
        listView = findViewById(R.id.act_mentor_list_view);
        MentorAdapter adapter = new MentorAdapter(this);
        listView.setAdapter(adapter);

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
}
