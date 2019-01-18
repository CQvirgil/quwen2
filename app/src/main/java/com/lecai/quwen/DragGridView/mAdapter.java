package com.lecai.quwen.DragGridView;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lecai.quwen.DragGridView.base.BaseItem;
import com.lecai.quwen.DragGridView.bean.ProvinceItem;
import com.lecai.quwen.R;

import java.util.List;

public class mAdapter extends BaseAdapter {

    Context context;
    List<ProvinceItem> list;

    public mAdapter(Context context, List<ProvinceItem> list) {
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
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        //view.setBackgroundColor(Color.parseColor("#ffffff"));
        TextView textview = view.findViewById(R.id.title);
        textview.setBackgroundResource(R.drawable.textview_bg);
        //textview.setPadding(20,10,20,10);
        textview.setText("+"+list.get(position).getName());
        return view;
    }

    public void removePosition(int position){
        list.remove(position);
        notifyDataSetChanged();
    }

    public void addItem(BaseItem item) {
        list.add((ProvinceItem) item);
        notifyDataSetChanged();
    }
}
