package com.lecai.quwen.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.lecai.quwen.AndroidRX.Rxid;
import com.lecai.quwen.MyApplication;
import com.lecai.quwen.NetWork.Client;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

import org.json.JSONException;
import org.json.JSONObject;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private Bundle bundle;
    private final String TAG = "WXEntryActivity_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WXUtil.getInstance().regToWx(this, MyApplication.getWxAppId());
        WXUtil.getInstance().api.handleIntent(getIntent(),this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        finish();
    }

    @Override
    public void onReq(BaseReq baseReq) {
        WXUtil.getInstance().api.handleIntent(getIntent(),this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    public void onResp(BaseResp baseResp) {
        switch(baseResp.errCode){
            case BaseResp.ErrCode.ERR_OK:
                Log.i(TAG,"onResp OK");
                if(baseResp instanceof SendAuth.Resp){
                    SendAuth.Resp newResp = (SendAuth.Resp) baseResp;
                    //获取微信传回的code
                    String code = newResp.code;
                    //RxBus.getInstance().send("WXcode:_"+code);
                    String url = "http://www.lecaigogo.com:4999/api/v1/user/wxlogin";
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("code",code);
                        Client.getInstance().PostServer(url,jsonObject,Rxid.GET_UUID);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.i(TAG,"onResp code = "+code);
                    Log.i(TAG,"onResp code = "+jsonObject.toString());
                }
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                Log.i(TAG,"onResp ERR_USER_CANCEL ");
                //发送取消
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                Log.i(TAG,"onResp ERR_AUTH_DENIED");
                //发送被拒绝
                break;
            default:
                Log.i(TAG,"onResp default errCode " + baseResp.errCode);
                //发送返回
                break;

        }
    }
}
