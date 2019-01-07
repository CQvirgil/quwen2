package com.lecai.quwen.MainActivity.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lecai.quwen.MyApplication;
import com.lecai.quwen.R;
import com.lecai.quwen.wxapi.WXUtil;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


@SuppressLint("ValidFragment")
public class MineFragment extends Fragment implements View.OnClickListener {
    private Context context;
    private Button login;
    private LinearLayout fgm_Mine_LL_user;
    public static Handler handler;
    public static final int SET_fgm_Mine_LL_user_VISIABLE = 1001,SET_USER_ICON = 1002;
    private static MineFragment fragment;
    private TextView user_name;
    private ImageView user_icon;
    private Bitmap bitmap;
    private SharedPreferences read;

    @SuppressLint({"ValidFragment", "CommitPrefEdits"})
    private MineFragment(Context context) {
        this.context = context;
    }

    // TODO: Rename and change types and number of parameters
    public static MineFragment newInstance(Context context) {
        if(fragment == null){
            fragment = new MineFragment(context);
        }
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WXUtil.getInstance().regToWx(getContext(), MyApplication.getWxAppId());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        initView(view);
        setLoginDiaLog();
        initLoginBtn();

        initHandler();
        read = getContext().getSharedPreferences("Setting", Context.MODE_PRIVATE);
        if(read.getBoolean("haslogin",false)&&getApp().getWXUser()!=null){
            handler.sendEmptyMessage(SET_fgm_Mine_LL_user_VISIABLE);
        }
        return view;
    }

    @SuppressLint("HandlerLeak")
    private void initHandler() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1001:
                        login.setVisibility(View.INVISIBLE);
                        fgm_Mine_LL_user.setVisibility(View.VISIBLE);
                        user_name.setText(getApp().getWXUser().getNickname());

                        Thread getHeadImage = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                bitmap = getURLimage(getApp().getWXUser().getHeadimgurl());
                                handler.sendEmptyMessage(1002);
                            }
                        });
                        getHeadImage.start();
                        break;
                    case 1002:
                        user_icon.setImageBitmap(bitmap);
                        break;
                }
            }
        };
    }

    //图片缩放
    private Bitmap resetBmp(){
        Bitmap bitmap = getURLimage(getApp().getWXUser().getHeadimgurl());
        if(bitmap!=null){
            float bmpwidth = bitmap.getWidth();
            float bmpheight = bitmap.getHeight();
            float scaleWidth = bmpwidth/2;
            float scaleheight = bmpheight/2;
            Matrix matrix = new Matrix();
            matrix.postScale(scaleWidth,scaleheight);
            Bitmap newbmp = Bitmap.createBitmap(bitmap,0,0,(int) bmpwidth,(int) bmpheight,matrix,true);
            return newbmp;
        }
        return null;
    }

    //加载图片
    public Bitmap getURLimage(String url) {
        Bitmap bmp = null;
        try {
            URL myurl = new URL(url);
            // 获得连接
            HttpURLConnection conn = (HttpURLConnection) myurl.openConnection();
            conn.setConnectTimeout(6000);//设置超时
            conn.setDoInput(true);
            conn.setUseCaches(false);//不缓存
            conn.connect();
            InputStream is = conn.getInputStream();//获得图片的数据流
            bmp = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bmp;
    }

    private void setLoginDiaLog() {
    }

    @SuppressLint("HandlerLeak")
    private void initView(View view) {
        login = view.findViewById(R.id.fgm_Mine_btn_login);
        fgm_Mine_LL_user = view.findViewById(R.id.fgm_Mine_LL_user);
        user_name = view.findViewById(R.id.user_name);
        user_icon = view.findViewById(R.id.user_icon);
    }

    private void initLoginBtn() {
        login.setOnClickListener(this);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fgm_Mine_btn_login:
                WXUtil.getInstance().loginToWX();
                break;
        }
    }

    private MyApplication getApp() {
        return (MyApplication) getContext().getApplicationContext();
    }
}
