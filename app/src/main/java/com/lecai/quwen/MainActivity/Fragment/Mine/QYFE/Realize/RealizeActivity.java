package com.lecai.quwen.MainActivity.Fragment.Mine.QYFE.Realize;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lecai.quwen.R;

public class RealizeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realize);
    }

    public void Back(View view) {
        finish();
    }
}
