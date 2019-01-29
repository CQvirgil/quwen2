package com.lecai.quwen.MainActivity.Fragment.Task.Mentor.BindingMaster;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lecai.quwen.R;

public class BindingMasterAdapter extends BaseAdapter {
    private Context context;

    public BindingMasterAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_act_binding_master,parent,false);
        return view;
    }
}
