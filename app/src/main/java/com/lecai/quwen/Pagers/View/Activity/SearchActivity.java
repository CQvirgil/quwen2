package com.lecai.quwen.Pagers.View.Activity;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.lecai.quwen.Bean.User;
import com.lecai.quwen.Pagers.View.Adapter.SearchRecyclerAdapter;
import com.lecai.quwen.R;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends ToolBarActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
    }

    public void initView() {
        setToolBar("搜索用户");
        setRecyclerView();
    }

    private void setRecyclerView() {
        recyclerView = findViewById(R.id.act_binding_maste_list);
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            list.add(new User (i+"",i+""));
        }
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new SearchRecyclerAdapter(list));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    }

}
