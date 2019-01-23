package com.lecai.quwen;

import android.app.Application;
import android.util.Log;

import com.lecai.quwen.Bean.Setting;
import com.lecai.quwen.Bean.WXUserBean;
import com.lecai.quwen.wxapi.WXUtil;
import com.yidian.newssdk.NewsFeedsSDK;

public class MyApplication extends Application {
    private static MyApplication instance;
    private static final String YI_DIAN_SDK_APP_ID = "j3cWV57eJ09MMfsiSgZOlw65";
    private static final String YI_DIAN_SDK_APP_KEY = "7YUK7x6ImqGXKpgQweB3ZsE2KdiXShcn";
    private static final String WX_APP_ID = "wx811763785c8899db";
    private static final String WX_AppSecret="511cfe2f9d2cbf742dfcb2eab24d4bab";
    private NewsFeedsSDK newsFeedsSDK;
    private WXUserBean WXUser;

    public static MyApplication getInstance(){
        if(instance!=null){
            return instance;
        }
        return null;
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
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.i("MyApplication","onTerminate");
    }

}
