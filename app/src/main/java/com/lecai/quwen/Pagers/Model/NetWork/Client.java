package com.lecai.quwen.Pagers.Model.NetWork;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.lecai.quwen.Pagers.Model.AndroidRX.RxBus;
import com.lecai.quwen.MyApplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Client {

    private static volatile Client mInstance;
    private static OkHttpClient client = null;
    Request request;
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

    public void PostServer(String url, JSONObject jsonObject, final String Rxid) throws JSONException {
        jsonObject.put("access_token",MyApplication.getInstance().getAccess_token());
        OkHttpClient okhttpClient = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody requestBody = RequestBody.create(null,jsonObject.toString());
        Request request = new Request.Builder()
                .url(url)
                .addHeader("content-type","application/json")
                .post(requestBody)
                .build();
        Call call = okhttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("WXEntryActivity_TAG","网络连接失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                StringBuffer stringBuffer = new StringBuffer(res);
                if(res!=null) {
                    RxBus.getInstance().send(Rxid+res);
                }
            }
        });
    }

    public void PostServerJ(String url, final JSONObject jsonObject, final String Rxid) throws JSONException {
        jsonObject.put("access_token",MyApplication.getInstance().getAccess_token());
        OkHttpClient okhttpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(null,jsonObject.toString());
        Request request = new Request.Builder()
                .url(url)
                .addHeader("content-type","application/json")
                .post(requestBody).build();
        Call call = okhttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("WXEntryActivity_TAG","网络连接失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                try {
                    JSONObject json_res = new JSONObject(res);
                    json_res.put("Rxid",Rxid);
                    if(json_res!=null) {
                        RxBus.getInstance().send(json_res);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void PostServerHeader(String url, JSONObject jsonObject, final String Rxid){
        OkHttpClient okhttpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),jsonObject.toString());
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization",MyApplication.getInstance().getUser().getToken())
                .post(requestBody).build();
        Log.i("WXEntryActivity_TAG",request.url().toString());
        Call call = okhttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("WXEntryActivity_TAG","网络连接失败" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                if(response.isSuccessful()){
                    if(res!=null) {
                        try {
                            JSONObject json_res = new JSONObject(res);
                            json_res.put("Rxid",Rxid);
                            if(response.isSuccessful()){
                                if(res!=null) {
                                    RxBus.getInstance().send(json_res);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    public void PostServerHeaderJ(String url, JSONObject jsonObject, final String Rxid){
        OkHttpClient okhttpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),jsonObject.toString());
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization",MyApplication.getInstance().getUser().getToken())
                .post(requestBody).build();
        Call call = okhttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("WXEntryActivity_TAG","网络连接失败");
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
                Log.i("WXEntryActivity_TAG","网络连接失败");
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

    public void getBitmap(String url, final String key){
        request = new Request.Builder().url(url).get().build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("WXEntryActivity_TAG","网络连接失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream res = response.body().byteStream();
                Bitmap bitmap = BitmapFactory.decodeStream(res);
                if(response.isSuccessful()){
                    if(res!=null){
                        RxBus.getInstance().send(bitmap);
                    }
                }
            }
        });
    }

}
