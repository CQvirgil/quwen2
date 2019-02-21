package com.lecai.quwen.MainActivity.Fragment.Mine.QYFE.Realize.Record;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lecai.quwen.Bean.Record;
import com.lecai.quwen.R;

import java.util.List;

public class RecordAdapter extends BaseAdapter {
    private Context context;
    private List<Record> list;

    public RecordAdapter(Context context, List<Record> list) {
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

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_act_record,parent,false);
        TextView status = view.findViewById(R.id.item_act_record_status);
        setStatus(status,list.get(position).getStatus());
        TextView money = view.findViewById(R.id.item_act_record_money);
        TextView number = view.findViewById(R.id.item_act_record_num);
        TextView date = view.findViewById(R.id.item_act_record_date);
        money.setText(list.get(position).getMoney()+"元");
        number.setText(list.get(position).getNumber()+"");
        date.setText(list.get(position).getDate());
        return view;
    }

    private void setStatus(TextView v_status,int status){
        switch (status){
            case 1:
                v_status.setText("正在申请");
                v_status.setTextColor(Color.parseColor("#5bb943"));
                v_status.setBackgroundResource(R.drawable.act_record_status1);
                break;
            case 2:
                v_status.setText("申请成功");
                v_status.setTextColor(Color.parseColor("#ea3535"));
                v_status.setBackgroundResource(R.drawable.act_record_status2);
                break;
            case 3:
                v_status.setText("申请失败");
                v_status.setTextColor(Color.parseColor("#999999"));
                v_status.setBackgroundResource(R.drawable.act_record_status3);
                break;
        }
    }
}
