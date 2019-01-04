package com.lecai.quwen.MainActivity;

import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.lecai.quwen.MainActivity.Fragment.HomepageFragment;
import com.lecai.quwen.MainActivity.Fragment.MallFragment;
import com.lecai.quwen.MainActivity.Fragment.MineFragment;
import com.lecai.quwen.MainActivity.Fragment.TaskFragment;
import com.lecai.quwen.R;

public class MainActivity extends AppCompatActivity {
    private RadioButton rb_home,rb_game,rb_mall,rb_task,rb_mine;
    private Fragment mfragments[];
    private Fragment mFrag;
    private ViewGroup.LayoutParams layoutParams;
    private RadioGroup mRadioGroup;
    private int mRadioGroup_height;
    public static Fragment A_Currentfragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initRadioButtonDrawable();
        initTab();
    }

    private void initView(){
        rb_home = findViewById(R.id.radio_button_home);
        rb_mall = findViewById(R.id.radio_button_mall);
        rb_task = findViewById(R.id.radio_button_task);
        rb_mine = findViewById(R.id.radio_button_mine);
        mRadioGroup = findViewById(R.id.radio_group_button);
    }

    private void initRadioButtonDrawable(){
        //定义底部标签图片大小和位置
        Drawable drawable_news = getResources().getDrawable(R.drawable.tab_home_selector);
        //当这个图片被绘制时，给他绑定一个矩形 ltrb规定这个矩形
        drawable_news.setBounds(0, 0, 80, 50);
        //设置图片在文字的哪个方向
        rb_home.setCompoundDrawables(null, drawable_news, null, null);


        //定义底部标签图片大小和位置
        Drawable drawable_mall = getResources().getDrawable(R.drawable.tab_attention_selector);
        //当这个图片被绘制时，给他绑定一个矩形 ltrb规定这个矩形
        drawable_mall.setBounds(0, 0, 80, 50);
        //设置图片在文字的哪个方向
        rb_mall.setCompoundDrawables(null, drawable_mall, null, null);

        //定义底部标签图片大小和位置
        Drawable drawable_task = getResources().getDrawable(R.drawable.tab_discovery_selector);
        //当这个图片被绘制时，给他绑定一个矩形 ltrb规定这个矩形
        drawable_task.setBounds(0, 0, 80, 50);
        //设置图片在文字的哪个方向
        rb_task.setCompoundDrawables(null, drawable_task, null, null);

        //定义底部标签图片大小和位置
        Drawable drawable_mine = getResources().getDrawable(R.drawable.tab_profile_selector);
        //当这个图片被绘制时，给他绑定一个矩形 ltrb规定这个矩形
        drawable_mine.setBounds(0, 0, 80, 50);
        //设置图片在文字的哪个方向
        rb_mine.setCompoundDrawables(null, drawable_mine, null, null);

    }


    private void initTab() {
        mfragments = new Fragment[5];
        mfragments[0] = HomepageFragment.newInstance(this);
        mfragments[1] = MallFragment.newInstance();
        mfragments[2] = MineFragment.newInstance(this);
        mfragments[3] = TaskFragment.newInstance();
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            Fragment Currentfragment = null;

            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.radio_button_home:
                        Currentfragment = mfragments[0];
                        loadFragment(0);
                        break;
                    case R.id.radio_button_mall:
                        Currentfragment = mfragments[1];
                        loadFragment(1);
                        break;
                    case R.id.radio_button_mine:
                        Currentfragment = mfragments[2];
                        loadFragment(2);
                        break;
                    case R.id.radio_button_task:
                        Currentfragment = mfragments[3];
                        loadFragment(3);
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

}
