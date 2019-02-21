package com.lecai.quwen.MainActivity.Fragment.Mine.Massage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lecai.quwen.MainActivity.Fragment.Mine.Massage.MessageDetail.MessageDetailActivity;
import com.lecai.quwen.R;

public class MassageActivity extends AppCompatActivity {
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_massage);
        listView = findViewById(R.id.act_mag_listview);
        MassageAdapter adapter = new MassageAdapter(this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setAction("startMessageDetailActivity");
                startActivity(intent);
            }
        });
    }

    public void Back(View view) {
        finish();
    }
}
