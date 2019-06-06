package com.lecai.quwen.Pagers.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lecai.quwen.Bean.Message;
import com.lecai.quwen.MyView.mTextView;
import com.lecai.quwen.R;

import java.util.List;

public class MassageAdapter extends BaseAdapter {
    Context context;
    List<Message> list;

    public MassageAdapter(Context context, List<Message> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_act_msg_listview,parent,false);
        mTextView title = view.findViewById(R.id.act_msg_title);
        if(position == 0){
            title.setDecorate(mTextView.Decorate.redpoint);
        }
        TextView textView = view.findViewById(R.id.act_msg_text);
        title.setText(list.get(position).getSub_name());
        textView.setText(list.get(position).getText());
        return view;
    }
}
