package quwen.lecai.com.quwen2;

import android.app.Application;

import com.yidian.newssdk.NewsFeedsSDK;

public class MyApplication extends Application {
    private static final String SDK_APP_ID = "j3cWV57eJ09MMfsiSgZOlw65";
    private static final String SDK_APP_KEY = "7YUK7x6ImqGXKpgQweB3ZsE2KdiXShcn";
    private NewsFeedsSDK newsFeedsSDK;

    @Override
    public void onCreate() {
        super.onCreate();
        newsFeedsSDK = new NewsFeedsSDK.Builder().setAppId(SDK_APP_ID).setAppKey(SDK_APP_KEY).setContext(getApplicationContext()).setDebugEnabled(true).build();
    }
}
