package com.lecai.quwen;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.lecai.quwen.AndroidRX.RxBus;
import com.lecai.quwen.Bean.Setting;
import com.lecai.quwen.Bean.User;
import com.lecai.quwen.Bean.WXUserBean;
import com.lecai.quwen.Service.MessageService;
import com.lecai.quwen.wxapi.WXUtil;
import com.yidian.newssdk.NewsFeedsSDK;

import org.json.JSONObject;

import io.reactivex.functions.Consumer;

public class MyApplication extends Application implements Consumer<JSONObject> {
    private static MyApplication instance;
    private static final String YI_DIAN_SDK_APP_ID = "j3cWV57eJ09MMfsiSgZOlw65";
    private static final String YI_DIAN_SDK_APP_KEY = "7YUK7x6ImqGXKpgQweB3ZsE2KdiXShcn";
    private static final String WX_APP_ID = "wx811763785c8899db";
    private static final String WX_AppSecret="511cfe2f9d2cbf742dfcb2eab24d4bab";
    private NewsFeedsSDK newsFeedsSDK;
    private WXUserBean WXUser;
    private User user;

    public static MyApplication getInstance(){
        if(instance!=null){
            return instance;
        }
        return null;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public WXUserBean getWXUser() {
        return WXUser;
    }

    public void setWXUser(WXUserBean WXUser) {
        this.WXUser = WXUser;
    }

    public static String getWxAppId() {
        return WX_APP_ID;
    }

    public static String getWX_AppSecret() {
        return WX_AppSecret;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        newsFeedsSDK = new NewsFeedsSDK.Builder()
                .setAppId(YI_DIAN_SDK_APP_ID)
                .setAppKey(YI_DIAN_SDK_APP_KEY)
                .setContext(getApplicationContext())
                .setDebugEnabled(true)
                .build();
        Setting.getInstance();
        if(instance == null){
            instance = this;
        }

        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);

        //RxBus.getInstance().subscribe(JSONObject.class,this);
    }



    @Override
    public void onTerminate() {
        super.onTerminate();
        RxBus.getInstance().unSubcribe();
        Log.i("MyApplication","onTerminate");
    }

    @Override
    public void accept(JSONObject jsonObject) throws Exception {
        Log.i("WXEntryActivity_TAG","app  "+jsonObject.toString());
    }
}
