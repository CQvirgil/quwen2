package com.lecai.quwen.Pagers.View.Adapter;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lecai.quwen.Bean.Message;
import com.lecai.quwen.Bean.Mine2Message;
import com.lecai.quwen.Pagers.View.Adapter.ViewHolder.MessageViewHolder;
import com.lecai.quwen.Pagers.View.Adapter.ViewHolder.ViewHolder;
import com.lecai.quwen.R;

import java.util.List;

public class MessagesAdapter extends BaseAdapter {
    List<Mine2Message> list;
    MessageViewHolder viewHolder;

    public MessagesAdapter(List<Mine2Message> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mine2_message, parent, false);
        //view.setOnClickListener(this);
        viewHolder = new MessageViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //super.onBindViewHolder(holder, position);
        Mine2Message message = list.get(position);
        ((MessageViewHolder)holder).setTag(position);
        ((MessageViewHolder)holder).setTitle(message.getTitle());
        ((MessageViewHolder)holder).setDate(message.getDate());
        if(position >= 3){
            ((MessageViewHolder)holder).setRead();
        }else{
            ((MessageViewHolder)holder).setUnRead();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }
}
