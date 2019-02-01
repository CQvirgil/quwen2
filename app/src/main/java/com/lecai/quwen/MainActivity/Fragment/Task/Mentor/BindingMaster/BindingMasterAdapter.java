package com.lecai.quwen.MainActivity.Fragment.Task.Mentor.BindingMaster;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.lecai.quwen.R;

public class BindingMasterAdapter extends BaseAdapter {
    private Context context;
    private boolean isadd = false;

    public boolean isIsadd() {
        return isadd;
    }

    public void setIsadd(boolean isadd) {
        this.isadd = isadd;
    }

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
        Button btn = view.findViewById(R.id.item_act_binding_master_btn);
        if(isadd){
            btn.setText("添加");
        }
        return view;
    }
}
