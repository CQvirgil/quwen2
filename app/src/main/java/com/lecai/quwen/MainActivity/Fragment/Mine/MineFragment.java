package com.lecai.quwen.MainActivity.Fragment.Mine;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lecai.quwen.DaiLog.UpDate;
import com.lecai.quwen.MyApplication;
import com.lecai.quwen.MyView.CircleImage;
import com.lecai.quwen.R;
import com.lecai.quwen.wxapi.WXUtil;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;



@SuppressLint("ValidFragment")
public class MineFragment extends Fragment implements View.OnClickListener {
    private Context context;
    private Button login;
    private LinearLayout fgm_Mine_LL_user,QYFE;
    private RelativeLayout Setting,Help,Msg,Update;
    public static Handler handler;
    public static final int SET_fgm_Mine_LL_user_VISIABLE = 1001,SET_USER_ICON = 1002;
    private static MineFragment fragment;
    private TextView user_name,user_u_id,user_gold;
    private CircleImage user_icon;
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
        initSetting();
        initHelp();
        initMsg();
        initHandler();
        initUpDate();
        read = getContext().getSharedPreferences("Setting", Context.MODE_PRIVATE);
        if(MyApplication.getInstance().getUser()!=null){
            handler.sendEmptyMessage(1003);
            user_icon.setImageURL(MyApplication.getInstance().getUser().getHead_img_url());
        }
        return view;
    }

    private void initMsg() {
        Msg.setOnClickListener(this);
    }

    @SuppressLint("HandlerLeak")
    private void initHandler() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1001:

                        break;
                    case 1002:

                        break;
                    case 1003:
                        if(MyApplication.getInstance().getUser()!=null){
                            login.setVisibility(View.INVISIBLE);
                            fgm_Mine_LL_user.setVisibility(View.VISIBLE);
                            user_name.setText(MyApplication.getInstance().getUser().getName());
                            user_u_id.setText("工号id："+MyApplication.getInstance().getUser().getUid());
                            user_gold.setText((int) MyApplication.getInstance().getUser().getGold()+"");
                        }
                        break;
                }
            }
        };
    }

    private void setLoginDiaLog() {
    }

    @Override
    public void onResume() {
        super.onResume();
        //handler.sendEmptyMessage(1003);
    }

    @SuppressLint("HandlerLeak")
    private void initView(View view) {
        login = view.findViewById(R.id.fgm_Mine_btn_login);
        fgm_Mine_LL_user = view.findViewById(R.id.fgm_Mine_LL_user);
        user_name = view.findViewById(R.id.user_name);
        user_icon = view.findViewById(R.id.user_icon);
        Setting = view.findViewById(R.id.item_set);
        Help = view.findViewById(R.id.item_help);
        Msg = view.findViewById(R.id.item_msg);
        Update = view.findViewById(R.id.item_update);
        QYFE = view.findViewById(R.id.fgm_Mine_ll_QYFE);
        user_u_id = view.findViewById(R.id.fgm_mine_text_useru_id);
        user_gold = view.findViewById(R.id.fgm_Mine_text_usertoken2);
        QYFE.setOnClickListener(this);
    }

    private void initSetting(){
        Setting.setOnClickListener(this);
    }

    private void initHelp(){
        Help.setOnClickListener(this);
    }

    private void initLoginBtn() {
        login.setOnClickListener(this);
    }

    private void initUpDate(){
        Update.setOnClickListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.fgm_Mine_btn_login:
                WXUtil.getInstance().loginToWX();
                break;
            case R.id.item_set:
                intent.setAction("startDragGridActivity");
                startActivity(intent);
                break;
            case R.id.item_help:
                intent.setAction("startHelpActivity");
                startActivity(intent);
                break;
            case R.id.item_msg:
                intent.setAction("startMassageActivity");
                startActivity(intent);
                break;
            case R.id.item_update:
                UpDate dialog = new UpDate(getContext());
                dialog.show();
                break;
            case R.id.fgm_Mine_ll_QYFE:
                intent.setAction("startQYFEActivity");
                startActivity(intent);
                break;
        }
    }

}
