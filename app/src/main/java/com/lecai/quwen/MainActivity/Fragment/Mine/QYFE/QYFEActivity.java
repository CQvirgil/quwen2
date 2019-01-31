package com.lecai.quwen.MainActivity.Fragment.Mine.QYFE;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lecai.quwen.R;

public class QYFEActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qyfe);
    }

    public void DirectionalGift(View view) {
        Intent intent = new Intent();
        intent.setAction("startDirectionalGiftActivity");
        startActivity(intent);
    }

    public void GoBack(View view) {
        finish();
    }

    public void startRealizeActivity(View view) {
        Intent intent = new Intent();
        intent.setAction("startRealizeActivity");
        startActivity(intent);
    }
}
