package com.lecai.quwen.SettingActivity.RecyclerAdapter;

import android.view.View;
import android.widget.Button;

import com.lecai.quwen.R;

public class ViewHolderChannel extends ViewHolder {
    private Button btn;
    public ViewHolderChannel(View itemView) {
        super(itemView);
        btn = itemView.findViewById(R.id.btn_channel);
    }
    public void setBtnText(String text){
        btn.setText(text);
    }
}
