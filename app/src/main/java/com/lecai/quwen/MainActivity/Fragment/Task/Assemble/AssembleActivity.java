package com.lecai.quwen.MainActivity.Fragment.Task.Assemble;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lecai.quwen.MyView.mGridView;
import com.lecai.quwen.R;

public class AssembleActivity extends AppCompatActivity {
    private mGridView mGridView1,mGridView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assemble);
        mGridView1 = findViewById(R.id.act_assemble_mgridview1);
        mGridView2 = findViewById(R.id.act_assemble_mgridview2);
        AssembleAdapter adapter = new AssembleAdapter(this);
        mGridView1.setAdapter(adapter);
        mGridView2.setAdapter(adapter);
    }

    public void Back(View view) {
        finish();
    }
}
