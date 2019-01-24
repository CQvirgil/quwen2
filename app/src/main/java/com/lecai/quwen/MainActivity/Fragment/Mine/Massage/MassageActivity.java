package com.lecai.quwen.MainActivity.Fragment.Mine.Massage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.lecai.quwen.R;

public class MassageActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_massage);
        listView = findViewById(R.id.act_mag_listview);
        MassageAdapter adapter = new MassageAdapter(this);
        listView.setAdapter(adapter);
    }

    public void Back(View view) {
        finish();
    }
}
