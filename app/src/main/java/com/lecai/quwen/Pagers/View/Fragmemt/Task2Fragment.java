package com.lecai.quwen.Pagers.View.Fragmemt;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lecai.quwen.Bean.Task2Item;
import com.lecai.quwen.Pagers.View.Adapter.SimpleAdapter;
import com.lecai.quwen.R;

import java.util.ArrayList;
import java.util.List;

public class Task2Fragment extends Fragment {
    private final String TAG = "Task2Fragment";
    private RecyclerView recyclerView;
    public Task2Fragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Task2Fragment newInstance() {
        Task2Fragment fragment = new Task2Fragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_task2, container, false);
        initView(view);
        return view;
    }

    private void initView(View view){
        getRecycler(view);
    }

    private void getRecycler(View view){
        recyclerView = view.findViewById(R.id.fgm_task2_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        List<Task2Item> item = new ArrayList<>();
        item.add(new Task2Item("阅读", 0,120,0,100));
        item.add(new Task2Item("位置打卡", 0,120,0,100));
        item.add(new Task2Item("拼团阅读", 0,120,0,100));
        item.add(new Task2Item("收徒", 0,120,0,100));
        SimpleAdapter adapter = new SimpleAdapter(item);
        adapter.setOnRecyclerItemClickListener(new SimpleAdapter.OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.i(TAG, "onItemClick: "+position);
            }
        });
        recyclerView.setAdapter(adapter);
    }

}
