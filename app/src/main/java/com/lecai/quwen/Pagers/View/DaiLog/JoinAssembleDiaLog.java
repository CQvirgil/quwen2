package com.lecai.quwen.Pagers.View.DaiLog;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lecai.quwen.Pagers.Model.AndroidRX.RxBus;
import com.lecai.quwen.Pagers.Model.AndroidRX.Rxid;
import com.lecai.quwen.MyApplication;
import com.lecai.quwen.Pagers.Model.NetWork.Client;
import com.lecai.quwen.R;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.functions.Consumer;

public class JoinAssembleDiaLog extends BaseDiaLog {
    private EditText editText;
    private Button btn;
    private TextView text_error,text_id;

    public JoinAssembleDiaLog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_join_assemble);
        initView();
    }
    private void initView(){
        editText = findViewById(R.id.dialog_join_assemble_edit_text);
        btn = findViewById(R.id.dialog_join_assemble_btn);
        text_error = findViewById(R.id.dialog_join_assemble_text_error);
        text_id = findViewById(R.id.dialog_join_assemble_text_id);
        setBtn();
    }

    private void setBtn(){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

}
