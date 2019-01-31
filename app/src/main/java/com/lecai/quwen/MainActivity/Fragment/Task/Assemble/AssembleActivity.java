package com.lecai.quwen.MainActivity.Fragment.Task.Assemble;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.lecai.quwen.DaiLog.CreateAssembleDiaLog;
import com.lecai.quwen.DaiLog.JoinAssembleDiaLog;
import com.lecai.quwen.DaiLog.OutAssembleDiaLog;
import com.lecai.quwen.DaiLog.OutAssembleSuccessDiaLog;
import com.lecai.quwen.MyView.mGridView;
import com.lecai.quwen.R;

public class AssembleActivity extends AppCompatActivity {
    private mGridView mGridView1,mGridView2;
    private CreateAssembleDiaLog diaLog1;
    private JoinAssembleDiaLog diaLog2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assemble);
        mGridView1 = findViewById(R.id.act_assemble_mgridview1);
        mGridView2 = findViewById(R.id.act_assemble_mgridview2);
        diaLog2 = new JoinAssembleDiaLog(this);
        diaLog1 = new CreateAssembleDiaLog(this);
        AssembleAdapter adapter = new AssembleAdapter(this);
        mGridView1.setAdapter(adapter);
        mGridView2.setAdapter(adapter);
        final OutAssembleDiaLog diaLog = new OutAssembleDiaLog(this);
        mGridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setAction("startMemberInformationActivity");
                startActivity(intent);
            }
        });
        mGridView1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                diaLog.show();
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        int show_dialog = intent.getIntExtra("show_dialog",0);
        switch (show_dialog){
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

    public void JoinAssemble(View view) {
        diaLog2.show();
    }
}
