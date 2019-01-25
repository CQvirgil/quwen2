package com.lecai.quwen.MainActivity.Fragment.Mine.Help;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lecai.quwen.R;

import java.util.List;

public class HelpListViewAdapter extends BaseAdapter {
    Context context;
    List<HelpBean> list;
    Boolean isclick = false;

    public HelpListViewAdapter(Context context) {
        this.context = context;
    }

    public HelpListViewAdapter(Context context, List<HelpBean> list) {
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

        View view = LayoutInflater.from(context).inflate(R.layout.item_act_help_listview,parent,false);
        TextView Q = view.findViewById(R.id.act_help_listview_item_title);
        Q.setText(position+1+"."+list.get(position).getQuestion());
        final TextView A = view.findViewById(R.id.act_help_listview_item_text);
        final ViewGroup.LayoutParams lp = A.getLayoutParams();
        final int AWidth = lp.width;
        A.setText(list.get(position).getAnswer());
        if(list.get(position).getAnswer().equals("")||!isclick){
            LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(AWidth,0);
            A.setLayoutParams(lp2);
        }
        final ImageButton imgbtn = view.findViewById(R.id.act_help_listview_item_imgbtn);

        imgbtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                if(isclick){
                    LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(AWidth,0);
                    A.setLayoutParams(lp2);
                    isclick = false;
                    imgbtn.setImageDrawable(context.getResources().getDrawable(R.drawable.imgbtn_act_help_right));
                }else{
                    A.setLayoutParams(lp);
                    isclick = true;
                    imgbtn.setImageDrawable(context.getResources().getDrawable(R.drawable.imgbtn_act_help_bottom));
                }
            }
        });
        return view;
    }
}
