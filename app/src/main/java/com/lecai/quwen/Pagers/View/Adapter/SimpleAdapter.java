package com.lecai.quwen.Pagers.View.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lecai.quwen.Bean.Task2Item;
import com.lecai.quwen.Pagers.View.Adapter.ViewHolder.Task2ItemViewHolder;
import com.lecai.quwen.Pagers.View.Adapter.ViewHolder.ViewHolder;
import com.lecai.quwen.R;

import java.util.List;

public class SimpleAdapter extends RecyclerView.Adapter<Task2ItemViewHolder> implements View.OnClickListener {
    private List<Task2Item> item;
    private Task2ItemViewHolder viewHolder;
    public static final int TASK2_ITEM = 0;
    private int type;
    public OnRecyclerItemClickListener onRecyclerItemClickListener;

    public SimpleAdapter(List<Task2Item> item) {
        this.item = item;
    }

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
    }

    @NonNull
    @Override
    public Task2ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        type = viewType;
        switch (viewType) {
            case TASK2_ITEM:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fgm_task2, parent, false);
                view.setOnClickListener(this);
                viewHolder = new Task2ItemViewHolder(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Task2ItemViewHolder holder, int position) {
        switch (type) {
            case TASK2_ITEM:
                setTask2Item(holder, position);
                break;
        }
    }

    private void setTask2Item(Task2ItemViewHolder holder, int position) {
        Task2Item p = item.get(position);
        holder.setTitle(p.getTitle());
        holder.setUnitNum(p.getUnit());
        holder.setUnitAll(p.getUnit_all(),position);
        holder.setValueAll(p.getValue_all());
        holder.setValueNum(p.getValue());
        holder.setTag(position);
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    @Override
    public void onClick(View v) {
        if(item != null){
            onRecyclerItemClickListener.onItemClick(v, (Integer) v.getTag());
        }
    }

    public interface OnRecyclerItemClickListener{
        void onItemClick(View view, int position);
    }
}
