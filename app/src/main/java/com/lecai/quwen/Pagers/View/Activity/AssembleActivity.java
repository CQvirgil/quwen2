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

public class AssembleActivity extends ToolBarActivity  {
    private mGridView mGridView1, mGridView2;
    private CreateAssembleDiaLog diaLog1;
    private JoinAssembleDiaLog diaLog2;
    public static Handler handler;
    private AssembleAdapter adapter_sub_team, adapter_dom_team;
    private RelativeLayout is_sub_team_none, is_dom_team_none;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assemble);
        setToolBar("拼团");
        diaLog1 = new CreateAssembleDiaLog(this);
        diaLog2 = new JoinAssembleDiaLog(this);
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

}
