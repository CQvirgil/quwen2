package com.lecai.quwen.Pagers.View.Activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

//Activity的基类
public class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate( Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    public void initView(){}
}
