package com.lecai.quwen.Pagers.View.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.lecai.quwen.Pagers.View.Adapter.ViewHolder.ViewHolder;

public class BaseAdapter extends RecyclerView.Adapter<ViewHolder> implements View.OnClickListener {
    private OnRecyclerItemListener listener;

    public OnRecyclerItemListener getListener() {
        return listener;
    }

    public void setListener(OnRecyclerItemListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.setTag(position);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void setItemClickable(View view){
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        listener.onItemClick(v, (Integer) v.getTag());
    }

    public interface OnRecyclerItemListener{
        void onItemClick(View v,int position);
    }
}
