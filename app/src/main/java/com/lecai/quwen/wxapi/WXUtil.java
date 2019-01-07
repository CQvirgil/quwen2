package com.lecai.quwen.wxapi;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.lecai.quwen.R;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXUtil {
    private static volatile WXUtil mInstance;
    public IWXAPI api;

    public WXUtil(){

    }

    public static WXUtil getInstance() {
        if (mInstance == null) {
            synchronized (WXUtil.class) {
                if (mInstance == null) {
                    mInstance = new WXUtil();
                }
            }
        }
        return mInstance;
    }

    //获取微信api对象
    public void regToWx(Context context,String APP_ID) {
        api = WXAPIFactory.createWXAPI(context, APP_ID, true);
    }


    //分享网页到朋友圈或微信好友
    public void sendWebPageToWX(boolean isSendToPYQ) {
        WXWebpageObject wxWebpageObject = new WXWebpageObject();
        wxWebpageObject.webpageUrl = "http://quwen.lecaigogo.com/";
        WXMediaMessage msg = new WXMediaMessage(wxWebpageObject);
        msg.title = "乐才趣闻";
        msg.description = "下载有奖";
//        Bitmap thumb = BitmapFactory.decodeResource(res,R.drawable.wabao_background);
//        msg.thumbData = BitmaptoBytearray(thumb);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;
        req.scene = isSendToPYQ?SendMessageToWX.Req.WXSceneSession:SendMessageToWX.Req.WXSceneTimeline;
        api.sendReq(req);
    }

    //分享图片到朋友圈或微信好友
    public void sendImageToWX(Resources resources,boolean isSendToPYQ,Bitmap shareBMP){
        Bitmap bmp = BitmapFactory.decodeResource(resources,R.drawable.ic_launcher_background);
        WXImageObject imageObject = new WXImageObject(shareBMP);
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = imageObject;
        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp,50,91,true);
        bmp.recycle();
        //msg.thumbData = BitmaptoBytearray(thumbBmp);//缩略图
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;
        req.scene = isSendToPYQ ? SendMessageToWX.Req.WXSceneTimeline:SendMessageToWX.Req.WXSceneSession;
        api.sendReq(req);
    }

    //分享文本到朋友圈或微信好友
    private void sendMessageToWXPengYouQuan() {
        WXTextObject wx_textobject = new WXTextObject();
        wx_textobject.text = "sadasdasdasdsadasdas";
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = wx_textobject;
        msg.description = "sadasdasdasdsadasdas";
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;
        req.scene = SendMessageToWX.Req.WXSceneTimeline;
        api.sendReq(req);
    }

    //微信授权登陆
    public void loginToWX(){
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo_test";
        api.sendReq(req);
    }

    //判断是否安装微信
    public boolean isWxAppInstalledAndSupported() {
        boolean bIsWXAppInstalledAndSupported = api .isWXAppInstalled();
        return bIsWXAppInstalledAndSupported;
    }


}
