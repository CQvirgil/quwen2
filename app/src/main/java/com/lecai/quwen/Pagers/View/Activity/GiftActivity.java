package com.lecai.quwen.Pagers.View.Activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lecai.quwen.R;

public class GiftActivity extends ToolBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift);
        setToolBar("定向赠送", "");
        setToolbarColor(Color.WHITE);
    }
}
