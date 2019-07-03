package com.lecai.quwen.Pagers.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lecai.quwen.Pagers.View.Adapter.ViewHolder.ViewHolder;
import com.lecai.quwen.R;

public class IntegralActivity extends ToolBarActivity implements View.OnClickListener {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integral);
        setToolBar("全部金币","安全密码");
        setToolbar_rightOnClick(this);
        initView();
    }

    public void initView(){
        recyclerView = findViewById(R.id.act_integral_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_record, parent, false);
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
    public void onClick(View v) {
        Toast.makeText(this, "onclick", Toast.LENGTH_SHORT).show();
    }

    public void onClickExchange(View view) {
        Intent intent = new Intent(IntegralActivity.this, ExchangeActivity.class);
        startActivity(intent);
    }

    public void onClickGiftBtn(View view) {
        Intent intent = new Intent(IntegralActivity.this, GiftActivity.class);
        startActivity(intent);
    }
}
