package com.lecai.quwen.MainActivity.Fragment.Mine.Massage.MessageDetail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lecai.quwen.R;

public class MessageDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_detail);
    }

    public void Back(View view) {
        finish();
    }
}
