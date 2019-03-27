package com.lecai.quwen.MainActivity.Fragment.Task.Mentor.BindingMaster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.lecai.quwen.R;

public class BindingMasterActivity extends AppCompatActivity {
    ListView listView;
    private EditText editText;
    private Button btn_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binding_master);
        listView = findViewById(R.id.act_binding_master_listview);
        TextView title = findViewById(R.id.act_binding_master_title);
        BindingMasterAdapter adapter = new BindingMasterAdapter(this);
        if(getIntent().getIntExtra("addApprentice",-1) == 1){
            title.setText("添加");
            adapter.setIsadd(true);
            listView.setAdapter(adapter);
        }else{
            adapter.setIsadd(false);
            listView.setAdapter(adapter);
        }
        initView();
        setBtn_search();
    }

    private void initView(){
        editText = findViewById(R.id.act_binding_master_edittext);
        btn_search = findViewById(R.id.act_binding_master_btn_search);
    }

    private void setBtn_search(){
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void Back(View view) {
        finish();
    }
}
