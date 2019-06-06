package com.lecai.quwen.Pagers.View.Adapter.ViewHolder;

import android.view.View;
import android.widget.Button;

import com.lecai.quwen.Pagers.View.Adapter.ViewHolder.ViewHolder;
import com.lecai.quwen.R;

public class ViewHolderAddChannel extends ViewHolder {
    Button btn;
    public ViewHolderAddChannel(View itemView) {
        super(itemView);
        btn = itemView.findViewById(R.id.btn_add);
    }

    public void setBtnText(String text){
        btn.setText(text);
    }
}
