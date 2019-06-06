package com.lecai.quwen.Pagers.View.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lecai.quwen.R;
import com.lecai.quwen.Pagers.View.Adapter.ViewHolder.ViewHolder;
import com.lecai.quwen.Pagers.View.Adapter.ViewHolder.ViewHolderAddChannel;
import com.lecai.quwen.Pagers.View.Adapter.ViewHolder.ViewHolderChannel;

import java.util.List;

public class ChannelAdapter extends RecyclerView.Adapter<ViewHolder> {
    private View v;
    private int type;
    private List<String> list;

    public ChannelAdapter(int type) {
        this.type = type;
    }

    public ChannelAdapter(int type, List<String> list) {
        this.type = type;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (type){
            case 0:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_channel,parent,false);
                break;
            case 1:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_channel_add,parent,false);
                break;
        }
        return type == 0?new ViewHolderChannel(v):new ViewHolderAddChannel(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(holder instanceof ViewHolderChannel&&list!=null){
            ((ViewHolderChannel)holder).setBtnText(list.get(position));
        }
        if(holder instanceof ViewHolderAddChannel&&list!=null){
            ((ViewHolderAddChannel)holder).setBtnText(list.get(position));
        }
    }

    //  添加数据
    public void addData(int position,String text) {
//      在list中添加数据，并通知条目加入一条
        list.add(position, text);
        //添加动画
        notifyItemInserted(position);
    }

    //  删除数据
    public void removeData(int position) {
        list.remove(position);
        //删除动画
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }
}
