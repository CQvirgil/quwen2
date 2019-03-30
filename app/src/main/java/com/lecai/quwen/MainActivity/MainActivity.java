package com.lecai.quwen.MainActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.lecai.quwen.AndroidRX.RxBus;
import com.lecai.quwen.AndroidRX.Rxid;
import com.lecai.quwen.Bean.User;
import com.lecai.quwen.Bean.WXUserBean;
import com.lecai.quwen.DragGridView.tools.Util;
import com.lecai.quwen.MainActivity.Fragment.NewsFragment;
import com.lecai.quwen.MainActivity.Fragment.MallFragment;
import com.lecai.quwen.MainActivity.Fragment.Mine.MineFragment;
import com.lecai.quwen.MainActivity.Fragment.Task.TaskFragment;
import com.lecai.quwen.MyApplication;
import com.lecai.quwen.NetWork.Client;
import com.lecai.quwen.R;
import com.lecai.quwen.Service.MessageService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import io.reactivex.functions.Consumer;

import static android.view.KeyEvent.KEYCODE_BACK;

public class MainActivity extends AppCompatActivity {
    private RadioButton rb_home,rb_task,rb_mine;
    private Fragment mfragments[];
    private Fragment mFrag;
    private ViewGroup.LayoutParams layoutParams;
    private RadioGroup mRadioGroup;
    private int mRadioGroup_height;
    public static Fragment A_Currentfragment;
    private String code;
    private JSONObject jsonObject;
    private String access_token;
    private String openid;
    private String refresh_token;
    private SharedPreferences.Editor editor;
    private SharedPreferences read;
    private String getWXuserURL;
    private Thread thread_timer;
    private int time;
    public static Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        initRadioButtonDrawable(rb_home,getResources().getDrawable(R.drawable.main_tab_news_selector));
        initRadioButtonDrawable(rb_task,getResources().getDrawable(R.drawable.main_tab_task_selector));
        initRadioButtonDrawable(rb_mine,getResources().getDrawable(R.drawable.main_tab_mine_selector));
        initTab();
        editor = this.getSharedPreferences("Setting", Context.MODE_PRIVATE).edit();
        read = this.getSharedPreferences("Setting", Context.MODE_PRIVATE);
        //Checkhaslogin();
        //WXlogin();
        //initTimer();
        initHandler();
        try {
            isLogin();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        login();

        //启动消息服务
//        Intent intent = new Intent(this,MessageService.class);
//        startService(intent);
    }

    private void isLogin() throws JSONException {
        if(read.getBoolean("haslogin",false)){
            User user = new User(read.getString("u_unionid",null),read.getString("token",null));
            MyApplication.getInstance().setUser(user);
            getUser();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //RxBus.getInstance().unSubcribe();
    }

    private void login(){
        RxBus.getInstance().subscribe(String.class, new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                String object = (String) o;
                //Log.i("WXEntryActivity_TAG", object);
                String rxid = object.substring(0,5);
                String data = object.substring(5);
                if(rxid.equals(Rxid.GET_UUID)){
                    if(MyApplication.getInstance().getUser()==null){
                        JSONObject json = new JSONObject(data);
                        User user = new User(json.getString("u_unionid"),json.getString("token"));
                        MyApplication.getInstance().setUser(user);
                    }
                    if(MyApplication.getInstance().getUser()!=null){
                        getUser();
                    }
                }else if(rxid.equals(Rxid.GET_USER)){

                    JSONObject json_user = new JSONObject(data);
                    if(json_user.getInt("return_code") == 1){
                        JSONObject json_user_data = json_user.getJSONObject("data");
                        if(MyApplication.getInstance().getUser()!=null){
                            MyApplication.getInstance().getUser().setUid(json_user_data.getString("uid"));
                            MyApplication.getInstance().getUser().setName(json_user_data.getString("name"));
                            MyApplication.getInstance().getUser().setGold(json_user_data.getInt("gold"));
                            MyApplication.getInstance().getUser().setHead_img_url(json_user_data.getString("headimg"));
                            editor.putBoolean("haslogin",true);
                            editor.putString("u_unionid",MyApplication.getInstance().getUser().getU_unionid());
                            editor.putString("token",MyApplication.getInstance().getUser().getToken());
                            editor.commit();
                        }
                    }
                }
            }
        });
    }

    private void getUser() throws JSONException {
        String url = "http://www.lecaigogo.com:4999/api/v1/user/user_info";
        JSONObject uuid = new JSONObject();
        uuid.put("u_unionid",MyApplication.getInstance().getUser().getU_unionid());
        Client.getInstance().PostServer(url,uuid, Rxid.GET_USER);
    }

    @SuppressLint("HandlerLeak")
    private void initHandler(){

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                switch (msg.what){
                    case 2100:
                        rb_home.setChecked(true);
                        break;
                }

            }
        };
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //Log.i("asdasdasd","onRestart");
    }

    //开辟线程用于计时
    private void initTimer(){
        thread_timer = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    if(A_Currentfragment instanceof NewsFragment ||A_Currentfragment instanceof MallFragment){
                        time++;
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Log.i("asdasdasdasda",time+"");
                    }
                }
            }
        });
        thread_timer.start();
    }

    private void Checkhaslogin() {
        access_token = read.getString("access_token", "null");
        openid = read.getString("openid", "null");
        refresh_token = read.getString("refresh_token", "null");
        Log.i("testrxbus", access_token + "  " + openid + " " + refresh_token);
        if (read.getBoolean("haslogin", false)) {
            if (!access_token.equals("null") || !openid.equals("null")) {
                Client.getInstance().getServer("https://api.weixin.qq.com/sns/auth?access_token=" + access_token +
                        "&openid=" + openid, "Checkaccess_token");
            }
        }
    }

    /*
     * access_token返回机制：
     * 1.首次登陆需要点击登陆按钮跳转到微信授权登陆，利用授权获得的code获取access_token直接用
     * access_token请求微信获取用户信息并将access_token缓存到手机当中。
     * 2.再次打开APP时，获取手机已存在的access_token，请求微信检查access_token是否超时，超时则利用refresh_token
     * 重新请求获取access_token，然后再次检查access_token是否超时，是则再次利用refresh_token获取，否则用access_token
     * 获取用户信息。
     * 3.refresh_token超时重新授权登陆
     * */

    private void WXlogin() {
        RxBus.getInstance().subscribe(String.class, new Consumer() {
            @SuppressLint("ApplySharedPref")
            @Override
            public void accept(Object o) throws Exception {
                String[] res = o.toString().split(":_");
                String key = res[0];
                String value = res[1];
                switch (key) {
                    case "test":
                        Log.i("testrxbus", value);
                        break;
                    case "WXcode"://获取授权后获得的code
                        code = value;
                        if (code != null) {
                            String getaccess_token_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
                                    "appid=" + MyApplication.getWxAppId() +
                                    "&secret=" + MyApplication.getWX_AppSecret() +
                                    "&code=" + code +
                                    "&grant_type=authorization_code";
                            Log.i("testrxbus", getaccess_token_URL);
                            Client.getInstance().getServer(getaccess_token_URL,"getaccess_token");
                        }
                        Log.i("testrxbus", code);
                        break;
                    case "getaccess_token"://获取access_token等数据
                        jsonObject = new JSONObject(value);
                        access_token = jsonObject.get("access_token").toString();
                        openid = jsonObject.get("openid").toString();
                        refresh_token = jsonObject.get("refresh_token").toString();
                        getWXuserURL = "https://api.weixin.qq.com/sns/userinfo?" +
                                "access_token=" + access_token +
                                "&openid=" + openid;
                        editor.putString("access_token", access_token);
                        editor.putString("openid", openid);
                        editor.putString("refresh_token", refresh_token);
                        editor.putBoolean("haslogin", true);
                        editor.commit();
                        Client.getInstance().getServer(getWXuserURL, "getWXuser");
                        Log.i("testrxbus", value);
                        break;
                    case "getWXuser":
                        Log.i("testrxbus", value);
                        if (!value.contains("errcode")) {
                            jsonObject = new JSONObject(value);
                            String WXusername = jsonObject.get("nickname").toString();
                            String headimgurl = jsonObject.get("headimgurl").toString();
                            int sex = jsonObject.getInt("sex");
                            String province = jsonObject.getString("province");
                            String city = jsonObject.getString("city");
                            String country = jsonObject.getString("country");
                            String privilege = jsonObject.getString("privilege");
                            String unionid = jsonObject.getString("unionid");
                            MyApplication.getInstance().setWXUser(new WXUserBean(WXusername, sex, province, city, headimgurl, country, privilege, unionid, 0));
                            if (MineFragment.handler != null) {
                                MineFragment.handler.sendEmptyMessage(MineFragment.SET_fgm_Mine_LL_user_VISIABLE);
                            }
                        } else {
                            Log.e("testrxbus", value);
                        }
                        break;
                    case "Checkaccess_token"://检查access_token是否可用
                        Log.i("testrxbus", value);
                        jsonObject = new JSONObject(value);
                        String errcode = jsonObject.get("errcode").toString();
                        if (errcode.equals("0")) {
                            if (!access_token.equals("null") || !openid.equals("null")) {
                                getWXuserURL = "https://api.weixin.qq.com/sns/userinfo?" +
                                        "access_token=" + access_token +
                                        "&openid=" + openid;
                            }
                            if (getWXuserURL != null) {
                                Client.getInstance().getServer(getWXuserURL, "getWXuser");
                            }
                        } else {
                            if (!access_token.equals("null") || !refresh_token.equals("null")) {
                                String refresh_tokenUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=" + MyApplication.getWxAppId() +
                                        "&grant_type=refresh_token&refresh_token=" + refresh_token;
                                Client.getInstance().getServer(refresh_tokenUrl, "refresh_tokenUrl");
                            }
                        }
                        break;
                    case "refresh_tokenUrl":
                        if (!value.contains("errcode")) {
                            Log.i("testrxbus", "refresh_tokenUrl   " + value);
                            jsonObject = new JSONObject(value);
                            access_token = jsonObject.get("access_token").toString();
                            openid = jsonObject.get("openid").toString();
                            refresh_token = jsonObject.get("refresh_token").toString();
                            editor.putString("access_token", access_token);
                            editor.putString("openid", openid);
                            editor.putString("refresh_token", refresh_token);
                            editor.commit();
                            Client.getInstance().getServer("https://api.weixin.qq.com/sns/auth?access_token=" + access_token +
                                    "&openid=" + openid, "Checkaccess_token");
                        } else {
                            Toast.makeText(MainActivity.this, "登陆超时请重新登陆", Toast.LENGTH_SHORT).show();
                            editor.putBoolean("haslogin", false);
                            editor.commit();
                        }
                        break;
                    case "api_lecai_getuser":
                        Log.i("api_lecai_getuser", value);
                        JSONObject json = new JSONObject(value);
                        if (json.getInt("ret") == 0) {
                            jsonObject = json.getJSONObject("data");
                            MyApplication.getInstance().getWXUser().setTOKEN(jsonObject.getInt("token"));
                        }
                        break;
                }
            }
        });
    }

    private void initView(){
        rb_home = findViewById(R.id.radio_button_home);
        rb_task = findViewById(R.id.radio_button_task);
        rb_mine = findViewById(R.id.radio_button_mine);
        mRadioGroup = findViewById(R.id.radio_group_button);
    }

    private void initRadioButtonDrawable(RadioButton radioButton,Drawable drawable){
        //定义底部标签图片大小和位置
        Drawable drawable_news = drawable;
        //当这个图片被绘制时，给他绑定一个矩形 ltrb规定这个矩形

        float width = Util.dip2px(this,25.0f);
        float height = Util.dip2px(this,25.0f);
        drawable_news.setBounds(0, 0, (int) width, (int) height);
        //设置图片在文字的哪个方向
        radioButton.setCompoundDrawables(null, drawable_news, null, null);


//        //定义底部标签图片大小和位置
//        Drawable drawable_task = getResources().getDrawable(R.drawable.main_tab_task_selector);
//        //当这个图片被绘制时，给他绑定一个矩形 ltrb规定这个矩形
//        drawable_task.setBounds(0, 0, 40, 40);
//        //设置图片在文字的哪个方向
//        rb_task.setCompoundDrawables(null, drawable_task, null, null);
//
//        //定义底部标签图片大小和位置
//        Drawable drawable_mine = getResources().getDrawable(R.drawable.main_tab_mine_selector);
//        //当这个图片被绘制时，给他绑定一个矩形 ltrb规定这个矩形
//        drawable_mine.setBounds(0, 0, 40, 40);
//        //设置图片在文字的哪个方向
//        rb_mine.setCompoundDrawables(null, drawable_mine, null, null);

    }


    private void initTab() {
        mfragments = new Fragment[5];
        mfragments[0] = NewsFragment.newInstance(this);
        mfragments[1] = MineFragment.newInstance(this);
        mfragments[2] = TaskFragment.newInstance();
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            Fragment Currentfragment = null;

            @SuppressLint("NewApi")
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.radio_button_home:
                        Currentfragment = mfragments[0];
                        loadFragment(0);
                        break;
                    case R.id.radio_button_mine:
                        if(read.getBoolean("haslogin",false)){
                            Currentfragment = mfragments[1];
                            loadFragment(1);
                        }else{
                            Intent intent = new Intent();
                            intent.setAction("startLogInActivity");
                            rb_home.setChecked(true);
                            startActivity(intent);
                        }
                        break;
                    case R.id.radio_button_task:
                        Currentfragment = mfragments[2];
                        loadFragment(2);
                        break;
                }
                if (Currentfragment != null) {
                    A_Currentfragment = Currentfragment;
                    //getSupportFragmentManager().beginTransaction().replace(R.id.home_container,Currentfragment).commitNow();
                }
            }
        });
        // 保证第一次会回调OnCheckedChangeListener
        rb_home.setChecked(true);
    }

    private void loadFragment(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
//从集合中获取相对序号的Fragment
        Fragment f = mfragments[position];
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//首先判断mFrag 是否为空，如果不为，先隐藏起来，接着判断从List 获取的Fragment是否已经添加到Transaction中，如果未添加，添加后显示，如果已经添加，直接显示
        if (mFrag != null) {
            fragmentTransaction.hide(mFrag);
        }
        if (!f.isAdded()) {
            fragmentTransaction.add(R.id.home_container, f);

        } else {
            fragmentTransaction.show(f);
        }
//将获取的Fragment 赋值给声明的Fragment 中，提交
        mFrag = f;
        fragmentTransaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.getInstance().unSubcribe();
        editor.commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch(keyCode){
            case KEYCODE_BACK:
                    finish();
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

}
