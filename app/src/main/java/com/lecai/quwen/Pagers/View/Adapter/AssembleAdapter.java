package com.lecai.quwen.Pagers.View.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lecai.quwen.Bean.Team;
import com.lecai.quwen.R;

import java.util.List;

public class AssembleAdapter extends BaseAdapter {
    Context context;
    private List<Team> list;

    public AssembleAdapter(Context context) {
        this.context = context;
    }

    public AssembleAdapter(Context context, List<Team> list) {
        this.context = context;
        this.list = list;
    }

    public List<Team> getList() {
        return list;
    }

    public void setList(List<Team> list) {
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
        TextView team_id = view.findViewById(R.id.item_act_assemble_id);
        team_id.setText("团ID:"+list.get(position).getTid());
        TextView team_name = view.findViewById(R.id.item_act_assemble_name);
        team_name.setText(list.get(position).getName());
        TextView team_num = view.findViewById(R.id.item_act_assemble_num);
        team_num.setText("激活进度:"+"0"+"/"+list.get(position).getNumber()+"(人)");
        return view;
    }
}
