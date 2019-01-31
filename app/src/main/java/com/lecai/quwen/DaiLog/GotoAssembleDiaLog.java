package com.lecai.quwen.DaiLog;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.lecai.quwen.R;

public class GotoAssembleDiaLog extends BaseDiaLog {
    public GotoAssembleDiaLog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_goto_assemble);Window window = this.getWindow();
        RelativeLayout root = findViewById(R.id.dialog_go_assemble_root);
//        window.setGravity(Gravity.BOTTOM);
//        WindowManager.LayoutParams params = window.getAttributes();
//        params.width = WindowManager.LayoutParams.MATCH_PARENT;
//        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        window.setAttributes(params);

        DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
        int window_width = metrics.widthPixels;
        ViewGroup.LayoutParams params1 = root.getLayoutParams();
        params1.width = window_width;
        root.setLayoutParams(params1);

        Button cancel = findViewById(R.id.btn_dialog_go_assemble_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
