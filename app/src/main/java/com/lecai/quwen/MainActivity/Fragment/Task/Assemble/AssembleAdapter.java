package com.lecai.quwen.MainActivity.Fragment.Task.Assemble;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lecai.quwen.R;

public class AssembleAdapter extends BaseAdapter {
    Context context;

    public AssembleAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_act_assemble_mgridview,parent,false);
        TextView status = view.findViewById(R.id.item_act_assemble_status);
        if(position == 0){
            status.setBackgroundResource(R.drawable.item_act_assemble_status_insufficient);
            status.setTextColor(Color.parseColor("#888888"));
            status.setText("人数不足");
        }else if(position == 1){
            status.setBackgroundResource(R.drawable.item_act_assemble_status_conduct);
            status.setTextColor(Color.parseColor("#ea3535"));
        }else {
            status.setBackgroundResource(R.drawable.item_act_assemble_status_success);
            status.setTextColor(Color.WHITE);
            status.setText("拼团成功");
        }

        return view;
    }
}
