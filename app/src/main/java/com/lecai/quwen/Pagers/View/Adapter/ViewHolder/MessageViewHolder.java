package com.lecai.quwen.Pagers.View.Adapter.ViewHolder;

import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import com.lecai.quwen.R;

public class MessageViewHolder extends ViewHolder {
    private View itemView, point;
    private TextView title, date;

    public MessageViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        initView(itemView);
    }

    private void initView(View v){
        title = v.findViewById(R.id.item_mine2_message_title);
        date = v.findViewById(R.id.item_mine2_message_date);
        point = v.findViewById(R.id.item_mine2_message_point);
    }

    public void setTitle(String str){
        title.setText(str);
    }

    public void setDate(String str){
        date.setText(str);
    }

    //设置为已读状态
    public void setRead(){
        point.setVisibility(View.INVISIBLE);
        title.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
    }
    //设置为未读状态
    public void setUnRead(){
        point.setVisibility(View.VISIBLE);
        title.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
    }
}
