package com.lecai.quwen.MainActivity.Fragment.Task.Assemble.MemberInformation;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.lecai.quwen.R;

import java.util.List;

public class MemberAdapter extends BaseAdapter {
    private Context context;
    private List<MemberBean> list;

    public MemberAdapter(Context context, List<MemberBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null?0:list.size();
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_act_member_information,parent,false);
        if(position == 0){
            ImageView highest = view.findViewById(R.id.item_act_member_highest);
            highest.setVisibility(View.VISIBLE);
        }

        return view;
    }

    public void addItem(MemberBean item){
        list.add(item);
        notifyDataSetChanged();
    }

}
