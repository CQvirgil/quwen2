package com.lecai.quwen.Pagers.View.Adapter.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ViewHolder extends RecyclerView.ViewHolder {
    private View itemView;

    public ViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
    }

    public void setTag(int position){
        itemView.setTag(position);
    }
}
