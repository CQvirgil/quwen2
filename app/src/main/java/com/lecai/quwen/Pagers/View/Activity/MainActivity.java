package com.lecai.quwen.Pagers.View.Activity;

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
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.lecai.quwen.DragGridView.tools.Util;
import com.lecai.quwen.Pagers.Presenter.IMainTabPresenter;
import com.lecai.quwen.Pagers.Presenter.IMainTabPresenterView;
import com.lecai.quwen.Pagers.Presenter.MainTabPresenterCompl;
import com.lecai.quwen.Pagers.View.Fragmemt.NewsFragment;
import com.lecai.quwen.Pagers.View.Fragmemt.MineFragment;
import com.lecai.quwen.Pagers.View.Fragmemt.TaskFragment;
import com.lecai.quwen.MyApplication;
import com.lecai.quwen.Pagers.Model.NetWork.HttpRequest;
import com.lecai.quwen.R;

import org.json.JSONException;

import static android.view.KeyEvent.KEYCODE_BACK;
//首页
public class MainActivity extends BaseActivity implements IMainTabPresenterView {
    private RadioButton rb_home, rb_task, rb_mine;
    private Fragment mfragments[];
    private Fragment mFrag;
    private RadioGroup mRadioGroup;
    private SharedPreferences.Editor editor;
    private SharedPreferences read;
    public static Handler handler;
    private IMainTabPresenter iMainTabPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        iMainTabPresenter = new MainTabPresenterCompl(this);
        initTab();
        initHandler();
    }

    @Override
    protected void onStart() {
        super.onStart();
        editor = this.getSharedPreferences("Setting", Context.MODE_PRIVATE).edit();
        read = this.getSharedPreferences("Setting", Context.MODE_PRIVATE);
    }

    private void isLogin() throws JSONException {
        if (read.getBoolean("haslogin", false)) {
            MyApplication.getInstance().setAccess_token(read.getString("access_token", null));
            MyApplication.getInstance().setRefresh_token(read.getString("refresh_token", null));
            MyApplication.getInstance().setU_unionid(read.getString("u_unionid", null));
            HttpRequest.getInstance().getUser();
        }
    }

    @SuppressLint("HandlerLeak")
    private void initHandler() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 2100:
                        rb_home.setChecked(true);
                        break;
                }
            }
        };
    }

    public void initView() {
        rb_home = findViewById(R.id.radio_button_home);
        rb_task = findViewById(R.id.radio_button_task);
        rb_mine = findViewById(R.id.radio_button_mine);
        mRadioGroup = findViewById(R.id.radio_group_button);
        initRadioButtonDrawable(rb_home, getResources().getDrawable(R.drawable.main_tab_news_selector));
        initRadioButtonDrawable(rb_task, getResources().getDrawable(R.drawable.main_tab_task_selector));
        initRadioButtonDrawable(rb_mine, getResources().getDrawable(R.drawable.main_tab_mine_selector));
    }

    private void initRadioButtonDrawable(RadioButton radioButton, Drawable drawable) {
        //定义底部标签图片大小和位置
        //当这个图片被绘制时，给他绑定一个矩形 ltrb规定这个矩形

        float width = Util.dip2px(this, 25.0f);
        float height = Util.dip2px(this, 25.0f);
        drawable.setBounds(0, 0, (int) width, (int) height);
        //设置图片在文字的哪个方向
        radioButton.setCompoundDrawables(null, drawable, null, null);
    }


    private void initTab() {
        mfragments = new Fragment[5];
        mfragments[0] = NewsFragment.newInstance(this);
        mfragments[1] = MineFragment.newInstance(this);
        mfragments[2] = TaskFragment.newInstance();
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @SuppressLint("NewApi")
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.radio_button_home:
                        iMainTabPresenter.NewsTabCheck();
                        break;
                    case R.id.radio_button_mine:
                        iMainTabPresenter.MineTabCheck();
                        break;
                    case R.id.radio_button_task:
                        iMainTabPresenter.TaskTabCheck();
                        break;
                }
            }
        });

        // 保证第一次会回调OnCheckedChangeListener
        rb_home.setChecked(true);
    }

    @Override
    public void loadFragment(int position) {
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
    public void onStartLoginActivity() {
        Intent intent = new Intent();
        intent.setAction("startLogInActivity");
        rb_home.setChecked(true);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        editor.commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KEYCODE_BACK:
                finish();
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

}
