package com.lecai.quwen.Pagers.View.Activity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.lecai.quwen.R;
import com.lecai.quwen.Pagers.View.Adapter.ChannelAdapter;
import com.lecai.quwen.Pagers.View.Adapter.ViewHolder.RecyclerViewClickListener2;

import java.util.ArrayList;
import java.util.List;

public class SettingActivity extends AppCompatActivity {
    private RecyclerView recyclerView,recyclerView_add;
    private String[] item;
    private List<String> chanle_list,item_channle;
    private ChannelAdapter channelAdapter;
    private SharedPreferences read;
    private SharedPreferences.Editor editor;
    private ChannelAdapter channeladdAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initList();
        initSharedPreferences();
        initrecyclerView();
        initrecyclerView_add();
    }

    @SuppressLint("CommitPrefEdits")
    private void initSharedPreferences(){
        editor = this.getSharedPreferences("channle",MODE_PRIVATE).edit();
        read = this.getSharedPreferences("channle",MODE_PRIVATE);
        if(read.getInt("size",-1) == -1){
            for(int i=0;i<6;i++){
                editor.putString(i+"",chanle_list.get(i));
                editor.putInt("size",i);
            }
            editor.commit();
        }
    }

    private void initList(){
        chanle_list = new ArrayList<>();
        chanle_list.add("推荐");
        chanle_list.add("热点");
        chanle_list.add("科技");
        chanle_list.add("娱乐");
        chanle_list.add("汽车");
        chanle_list.add("旅游");
        chanle_list.add("情感");
    }

    private void initrecyclerView(){
        recyclerView = findViewById(R.id.recycler_channel);
        item_channle = new ArrayList<>();
        for(int d=0;d<read.getInt("size",-1)+1;d++){
            item_channle.add(read.getString(d+"",null));
        }
        for(int i = 0; i< chanle_list.size(); i++){
            for(int j=0;j<item_channle.size();j++){
                if(chanle_list.get(i).equals(item_channle.get(j))){
                    chanle_list.remove(i);
                }
            }
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,5);
        channelAdapter = new ChannelAdapter(0,item_channle);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(channelAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerViewClickListener2(this, recyclerView, new RecyclerViewClickListener2.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position,float x,float y) {
                int size = read.getInt("size",-1);
                if(size>=3){
                    for(int n=0;n<=size;n++){
                        editor.remove(n+"");
                    }
                    editor.commit();
                    Button btn = view.findViewById(R.id.btn_channel);
                    channelAdapter.removeData(position);
                    channeladdAdapter.addData(chanle_list.size(),btn.getText().toString());
                    size--;
                    editor.putInt("size",size);
                    for(int i=0;i<item_channle.size();i++){
                        editor.putString(i+"",item_channle.get(i));
                    }
                    editor.commit();
                }

                //Toast.makeText(SettingActivity.this, item_channle.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
    }

    private void initrecyclerView_add(){
        recyclerView_add = findViewById(R.id.recycler_add_channel);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,5);
        channeladdAdapter = new ChannelAdapter(1, chanle_list);
        recyclerView_add.setLayoutManager(gridLayoutManager);
        recyclerView_add.setAdapter(channeladdAdapter);
        recyclerView_add.addOnItemTouchListener(new RecyclerViewClickListener2(this, recyclerView_add, new RecyclerViewClickListener2.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position,float x,float y) {
                Button btn =  view.findViewById(R.id.btn_add);
                channeladdAdapter.removeData(position);
                channelAdapter.addData(item_channle.size(),btn.getText().toString());
                int size = read.getInt("size",-1)+1;
                editor.putString(size+"",btn.getText().toString());
                editor.putInt("size",size);
                editor.commit();
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
    }
}
