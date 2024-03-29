package com.lecai.quwen.Pagers.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lecai.quwen.Bean.Apprentice;
import com.lecai.quwen.MyView.CircleImage;
import com.lecai.quwen.R;

import java.util.List;

public class MentorAdapter extends BaseAdapter {
    private Context context;
    private List<Apprentice> list;

    public MentorAdapter(Context context, List<Apprentice> list) {
        this.context = context;
        this.list = list;
    }

    public void setList(List<Apprentice> list){
        this.list = list;
        notifyDataSetInvalidated();
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_act_mentor_listview,parent,false);
        TextView name, id;
        name = view.findViewById(R.id.item_act_mentor_name);
        id = view.findViewById(R.id.item_act_mentor_id);
        name.setText(list.get(position).getName());
        id.setText("ID:"+list.get(position).getUid());
        CircleImage headimg = view.findViewById(R.id.act_mentor_listview_item_headimg);
        headimg.setImageURL(list.get(position).getUrl());
        return view;
    }
}
