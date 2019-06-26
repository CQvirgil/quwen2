package com.lecai.quwen.Pagers.View.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;

import android.widget.ListView;

import com.lecai.quwen.Pagers.Model.AndroidRX.RxBus;
import com.lecai.quwen.R;


public class MassageActivity extends AppCompatActivity {
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_massage);
        listView = findViewById(R.id.act_mag_listview);
    }

    public void Back(View view) {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.getInstance().unSubcribe();
    }
}
