package com.lecai.quwen.Pagers.View.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.lecai.quwen.Bean.User;
import com.lecai.quwen.Pagers.Model.AndroidRX.Rxid;
import com.lecai.quwen.Bean.MasterBean;
import com.lecai.quwen.MyApplication;
import com.lecai.quwen.MyView.CircleImage;
import com.lecai.quwen.Pagers.Model.NetWork.Client;
import com.lecai.quwen.Pagers.View.Adapter.ViewHolder.SearchRecyclerViewHolder;
import com.lecai.quwen.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class SearchRecyclerAdapter extends RecyclerView.Adapter<SearchRecyclerViewHolder> {
    private List<User> list;
    private SearchRecyclerViewHolder viewHolder = null;
    private int type = 0;
    public static int VIEW_TYPE_DEFAULT = 0;

    public SearchRecyclerAdapter(List<User> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public SearchRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        type = viewType;
        switch (viewType) {
            case 0:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_act_binding_master, parent, false);
                viewHolder = new SearchRecyclerViewHolder(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchRecyclerViewHolder holder, int position) {
        switch (type) {
            case 0:
                break;
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


}
