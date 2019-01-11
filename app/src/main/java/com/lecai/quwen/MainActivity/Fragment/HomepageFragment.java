package com.lecai.quwen.MainActivity.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;

import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lecai.quwen.R;
import com.yidian.newssdk.exportui.NewsListForViewPagerFragment;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;


@SuppressLint("ValidFragment")
public class HomepageFragment extends Fragment {
    private Context context;
    private ViewPager viewPager;
    private TabLayout tab;
    public static List<String> tabTilte;
    private List<ChannelFragment> fragments;
    private SharedPreferences read;
    private Button btn;
    private ViewPagerAdapter viewPagerAdapter;

    @SuppressLint("ValidFragment")
    public HomepageFragment(Context context) {
        this.context = context;
    }

    public static HomepageFragment newInstance(Context context) {
        HomepageFragment fragment = new HomepageFragment(context);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        read = getContext().getSharedPreferences("channle",MODE_PRIVATE);
        Log.i("asdasdasdasd","onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        initTabLayout();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initTabTitle();
        initViewPager();
    }

    private void initTabTitle(){
        tabTilte = new ArrayList<>();
        int size = read.getInt("size",-1);
        if(size!=-1){
            for(int i=0;i<=size;i++){
                tabTilte.add(read.getString(i+"",null));
            }
        }else{
            initList();
        }
    }

    private void initList(){
        tabTilte.add("推荐");
        tabTilte.add("热点");
        tabTilte.add("科技");
        tabTilte.add("娱乐");
        tabTilte.add("汽车");
        tabTilte.add("旅游");
        tabTilte.add("情感");
    }

    private void initView(View view){
        tab = view.findViewById(R.id.tab);
        viewPager = view.findViewById(R.id.view_pager);
    }

    private void initViewPager(){
        fragments = new ArrayList<>();
        for(int i=0;i<tabTilte.size();i++){
            fragments.add(ChannelFragment.newInstance(tabTilte.get(i)));
        }
        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(),fragments);
        viewPager.setAdapter(viewPagerAdapter);
    }

    private void initTabLayout(){
        tab.setupWithViewPager(viewPager);
        //设置可以滑动
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

}