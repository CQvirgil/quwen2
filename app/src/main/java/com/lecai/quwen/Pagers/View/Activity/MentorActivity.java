package com.lecai.quwen.Pagers.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

import com.lecai.quwen.Pagers.Model.AndroidRX.RxBus;
import com.lecai.quwen.Pagers.Model.AndroidRX.Rxid;
import com.lecai.quwen.Bean.Apprentice;
import com.lecai.quwen.Pagers.View.DaiLog.GotoAssembleDiaLog;
import com.lecai.quwen.Pagers.View.Adapter.MentorAdapter;
import com.lecai.quwen.MyApplication;
import com.lecai.quwen.MyView.CircleImage;
import com.lecai.quwen.Pagers.Model.NetWork.Client;
import com.lecai.quwen.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

public class MentorActivity extends ToolBarActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor);
        setToolBar("师徒");
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
