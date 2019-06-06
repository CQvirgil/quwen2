package com.lecai.quwen.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.lecai.quwen.Pagers.Model.AndroidRX.Rxid;
import com.lecai.quwen.MyApplication;
import com.lecai.quwen.Pagers.Model.NetWork.Client;

import org.json.JSONException;
import org.json.JSONObject;

public class MessageService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            getMessage();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("WXEntryActivity_TAG","MessageServiceCreate");
    }

    private void getMessage() throws JSONException {
        String url = "http://www.lecaigogo.com:4999/api/v1/news/disciple_news";
        JSONObject json_post = new JSONObject();
        json_post.put("u_unionid",MyApplication.getInstance().getUser().getU_unionid());
        Client.getInstance().PostServerHeader(url,json_post,Rxid.GET_MESSAGE);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
