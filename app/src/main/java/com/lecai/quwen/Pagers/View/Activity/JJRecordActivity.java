package com.lecai.quwen.Pagers.View.Activity;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lecai.quwen.Pagers.View.Adapter.ViewHolder.ViewHolder;
import com.lecai.quwen.R;

public class JJRecordActivity extends ToolBarActivity {
    private RecyclerView records;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record2);
        setToolBar("竞价记录", "");
        setToolbarColor(Color.WHITE);
        settoolbarTitleColor(Color.BLACK);
        records = findViewById(R.id.act_jjrecord_records);
        records.setLayoutManager(new LinearLayoutManager(this));
        records.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_jjrecord, parent, false);
                RecyclerView.ViewHolder viewHolder = new ViewHolder(view);
                return viewHolder;
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 5;
            }
        });
    }
}
