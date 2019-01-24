package com.lecai.quwen.MainActivity.Fragment.Mine.Massage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lecai.quwen.MyView.mTextView;
import com.lecai.quwen.R;

public class MassageAdapter extends BaseAdapter {
    Context context;

    public MassageAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 6;
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_act_msg_listview,parent,false);
        mTextView title = view.findViewById(R.id.act_msg_title);
        if(position == 0){
            title.setDecorate(mTextView.Decorate.redpoint);
        }
        return view;
    }
}
