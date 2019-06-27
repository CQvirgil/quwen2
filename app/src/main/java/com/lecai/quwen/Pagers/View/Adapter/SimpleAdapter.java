package com.lecai.quwen.Pagers.View.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.lecai.quwen.Pagers.View.Adapter.ViewHolder.ViewHolder;
import com.lecai.quwen.R;

import java.util.List;

public class SimpleAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<Object> item;
    private ViewHolder viewHolder;
    public static final int TASK2_ITEM = 0;


    public SimpleAdapter(List<Object> item) {
        this.item = item;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case TASK2_ITEM:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fgm_task2, parent, false);
                viewHolder = new ViewHolder(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return item.size();
    }
}
