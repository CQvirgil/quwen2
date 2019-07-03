package com.lecai.quwen.Pagers.View.Activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lecai.quwen.Pagers.View.Adapter.ViewHolder.ViewHolder;
import com.lecai.quwen.Pagers.View.DaiLog.CreateAssembleDiaLog;
import com.lecai.quwen.Pagers.View.DaiLog.JoinAssembleDiaLog;
import com.lecai.quwen.R;


public class AssembleActivity extends ToolBarActivity  {
    private CreateAssembleDiaLog diaLog1;
    private JoinAssembleDiaLog diaLog2;
    public static Handler handler;
    private TextView income_hint;
    private RecyclerView assembles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assemble);
        setToolBar("拼团", "");
        initView();
        diaLog1 = new CreateAssembleDiaLog(this);
        diaLog2 = new JoinAssembleDiaLog(this);
    }

    public void initView(){
        income_hint = findViewById(R.id.income_hit);
        income_hint.setText("每天可获得团成员阅读收入的5%");
        assembles = findViewById(R.id.act_assemble_assembles);
        assembles.setLayoutManager(new LinearLayoutManager(this));
        assembles.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_assemble, parent, false);
                return new ViewHolder(view);
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 10;
            }
        });
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
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void JoinAssemble(View view) {
        diaLog2.show();
    }

}
