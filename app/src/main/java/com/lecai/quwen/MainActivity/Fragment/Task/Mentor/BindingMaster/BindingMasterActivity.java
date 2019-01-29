package com.lecai.quwen.MainActivity.Fragment.Task.Mentor.BindingMaster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.lecai.quwen.R;

public class BindingMasterActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binding_master);
        listView = findViewById(R.id.act_binding_master_listview);
        BindingMasterAdapter adapter = new BindingMasterAdapter(this);
        listView.setAdapter(adapter);
    }

    public void Back(View view) {
        finish();
    }
}
