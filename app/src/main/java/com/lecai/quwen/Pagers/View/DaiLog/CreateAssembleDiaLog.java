package com.lecai.quwen.Pagers.View.DaiLog;

import android.content.Context;
import android.os.Bundle;
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

public class CreateAssembleDiaLog extends BaseDiaLog {
    private EditText editText;
    private TextView textView;
    private Button button;
    public CreateAssembleDiaLog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_create_assemble);
        initView();
        setButton();
    }

    private void initView(){
        editText = findViewById(R.id.dialog_create_assemble_edit_text);
        textView = findViewById(R.id.dialog_create_assemble_text_err);
        button = findViewById(R.id.dialog_create_assemble_btn);
    }

    private void setButton(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

}
