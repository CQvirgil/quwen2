package com.lecai.quwen.MainActivity.Fragment.Mine.QYFE.Realize.Record;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.lecai.quwen.Bean.Record;
import com.lecai.quwen.R;

import java.util.ArrayList;
import java.util.List;

public class RecordActivity extends AppCompatActivity {
    private ListView record_listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        record_listview = findViewById(R.id.act_record_listview);
        List<Record> list = new ArrayList<>();
        list.add(new Record(1,213,123,"10-23"));
        list.add(new Record(2,213,123,"10-23"));
        list.add(new Record(3,213,123,"10-23"));
        list.add(new Record(2,213,123,"10-23"));
        RecordAdapter adapter = new RecordAdapter(this,list);
        record_listview.setAdapter(adapter);
    }

    public void Back(View view) {
        finish();
    }
}
