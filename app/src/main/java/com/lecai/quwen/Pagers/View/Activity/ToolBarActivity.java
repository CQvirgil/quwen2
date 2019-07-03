package com.lecai.quwen.Pagers.View.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.lecai.quwen.R;


public class ToolBarActivity extends BaseActivity {
    private Toolbar toolbar;
    private TextView toolbar_title, toolbar_right;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void setToolbarColor(int color){
        toolbar.setBackgroundColor(color);
    }

    public void settoolbarTitleColor(int color){
        toolbar_right.setTextColor(color);
        toolbar_title.setTextColor(color);
    }

    public void setToolbar_rightOnClick(View.OnClickListener onClickListener){
        toolbar_right.setOnClickListener(onClickListener);
    }

    public void setToolBar(String title,String right){
        toolbar = findViewById(R.id.toolbar);
        toolbar_title = toolbar.findViewById(R.id.toolbar_title);
        toolbar_right = findViewById(R.id.toolbar_right);
        toolbar_title.setText(title);
        toolbar_right.setText(right);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
}
