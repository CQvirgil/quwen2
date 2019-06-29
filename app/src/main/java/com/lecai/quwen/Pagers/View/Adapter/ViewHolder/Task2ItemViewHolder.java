package com.lecai.quwen.Pagers.View.Adapter.ViewHolder;

import android.view.View;
import android.widget.TextView;

import com.lecai.quwen.R;

public class Task2ItemViewHolder extends ViewHolder {
    private TextView tv_title, tv_unit, tv_unit_all, tv_value, tv_value_all;
    private View itemView;

    public Task2ItemViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        initView(itemView);
    }

    private void initView(View view){
        tv_title = view.findViewById(R.id.fgm_task2_item_title);
        tv_unit = view.findViewById(R.id.fgm_task2_item_unit);
        tv_unit_all = view.findViewById(R.id.fgm_task2_item_unit_all);
        tv_value = view.findViewById(R.id.fgm_task2_item_value);
        tv_value_all = view.findViewById(R.id.fgm_task2_item_value_all);
    }

    public void setTag(int position){
        this.itemView.setTag(position);
    }

    public void setTitle(String str){
        tv_title.setText(str);
    }

    public void setUnitAll(float num,int position){
        tv_unit_all.setText("/"+num+getUnit(position));
    }

    private String getUnit(int position){
        String unit = "";
        switch (position){
            case 0:
                unit = "分钟";
                break;
            case 1:
                unit = "次";
                break;
            case 2:
                unit = "团";
                break;
            case 3:
                unit = "个";
                break;
        }
        return unit;
    }

    public void setUnitNum(float num){
        tv_unit.setText(num+"");
    }

    public void setValueAll(float num){
        tv_value_all.setText("/"+num);

    }

    public void setValueNum(float num){
        tv_value.setText(num+"");
    }
}
