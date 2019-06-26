package com.lecai.quwen.Pagers.View.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lecai.quwen.Pagers.Model.AndroidRX.RxBus;
import com.lecai.quwen.Pagers.Model.AndroidRX.Rxid;
import com.lecai.quwen.MyApplication;
import com.lecai.quwen.Pagers.Model.NetWork.Client;
import com.lecai.quwen.R;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.functions.Consumer;

public class MessageDetailActivity extends ToolBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_detail);
        setToolBar("消息中心");
    }

}
