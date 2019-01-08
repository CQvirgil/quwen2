package com.lecai.quwen.SettingActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lecai.quwen.R;
import com.lecai.quwen.SettingActivity.RecyclerAdapter.AdapterChannel;
import com.lecai.quwen.SettingActivity.RecyclerAdapter.RecyclerViewClickListener2;
import com.yidian.newssdk.libraries.bra.BaseQuickAdapter;
import com.yidian.newssdk.libraries.bra.listener.SimpleClickListener;

import java.util.ArrayList;
import java.util.List;

public class SettingActivity extends AppCompatActivity {
    private RecyclerView recyclerView,recyclerView_add;
    private String[] item;
    private List<String> list,item_channle,add_channle;
    private AdapterChannel adapterChannel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initList();
        initrecyclerView();
        initrecyclerView_add();
    }

    private void initList(){
        list = new ArrayList<>();
        list.add("推荐");
        list.add("热点");
        list.add("科技");
        list.add("娱乐");
        list.add("汽车");
        list.add("旅游");
        list.add("情感");
    }

    private void initrecyclerView(){
        recyclerView = findViewById(R.id.recycler_channel);
        item_channle = new ArrayList<>();
        item_channle.add("推荐");
        item_channle.add("热点");
        item_channle.add("科技");
        for(int i=0;i<list.size();i++){
            for(int j=0;j<item_channle.size();j++){
                if(list.get(i).equals(item_channle.get(j))){
                    list.remove(i);
                }
            }
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,5);
        adapterChannel = new AdapterChannel(0,item_channle);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapterChannel);
    }

    private void initrecyclerView_add(){
        recyclerView_add = findViewById(R.id.recycler_add_channel);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,5);
        final AdapterChannel adapterChanneladd = new AdapterChannel(1,list);
        recyclerView_add.setLayoutManager(gridLayoutManager);
        recyclerView_add.setAdapter(adapterChanneladd);
        recyclerView_add.addOnItemTouchListener(new RecyclerViewClickListener2(this, recyclerView_add, new RecyclerViewClickListener2.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Button btn =  view.findViewById(R.id.btn_add);
                adapterChanneladd.removeData(position);
                adapterChannel.addData(item_channle.size(),btn.getText().toString());
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
    }
}
