package com.lecai.quwen.Pagers.View.Activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;


public class ToolBarActivity extends BaseActivity {
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
}
