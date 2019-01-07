package com.lecai.quwen.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.lecai.quwen.AndroidRX.RxBus;
import com.lecai.quwen.MyApplication;
import com.lecai.quwen.NetWork.Client;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

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
                    RxBus.getInstance().send("WXcode:_"+code);
                    Log.i(TAG,"onResp code = "+code);
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
