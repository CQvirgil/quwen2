package com.lecai.quwen.MainActivity.Fragment.Task.Ranking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lecai.quwen.R;

public class RankingAdapter extends BaseAdapter {
    Context context;

    public RankingAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 10;
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_act_ranking,parent,false);
        TextView num = view.findViewById(R.id.item_act_ranking_num);
        num.setText(position+1+"");
        return view;
    }
}
