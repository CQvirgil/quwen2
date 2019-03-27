package com.lecai.quwen.NetWork;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.lecai.quwen.AndroidRX.RxBus;
import com.lecai.quwen.AndroidRX.Rxid;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Client {

    private static volatile Client mInstance;
    private static OkHttpClient client = null;
    Request request;
    String Tag = "ClientTAG";
    String res;
    public Client() {
        client = new OkHttpClient();
    }

    public static Client getInstance() {
        if (mInstance == null) {
            synchronized (Client.class) {
                if (mInstance == null) {
                    mInstance = new Client();
                }
            }
        }
        return mInstance;
    }

    public void PostServer(String url, JSONObject jsonObject, final String Rxid){
        OkHttpClient okhttpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),jsonObject.toString());
        Request request = new Request.Builder().url(url).post(requestBody).build();
        Call call = okhttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(Tag,"网络连接失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                if(response.isSuccessful()){
                    if(res!=null) {
                        RxBus.getInstance().send(Rxid+res);
                    }
                }
            }
        });
    }

    public void getServer(String url, final String key){
        request = new Request.Builder().url(url).get().build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(Tag,"网络连接失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                if(response.isSuccessful()){
                    if(res!=null){
                        Map<String,String> map = new HashMap<>();
                        map.put(key,res);
                        RxBus.getInstance().send(key+":_"+res);
                    }
                }
            }
        });
    }

}
