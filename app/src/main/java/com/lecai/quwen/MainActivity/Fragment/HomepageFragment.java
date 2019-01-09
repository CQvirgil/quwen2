package com.lecai.quwen.MainActivity.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.lecai.quwen.R;
import com.yidian.newssdk.exportui.NewsListForViewPagerFragment;
import com.yidian.newssdk.exportui.NewsListFragment;
import com.yidian.newssdk.exportui.NewsPortalFragment;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;


@SuppressLint("ValidFragment")
public class HomepageFragment extends Fragment {
    private Context context;
    private NewsListFragment fragmentNavi;
    private ViewPager viewPager;
    private TabLayout tab;
    public static List<String> tabTilte;
    public List<Fragment> fragments;
    private SharedPreferences read;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        initTabTitle();
        initViewPager();
        initTabLayout();
        return view;
    }

    private void initTabTitle(){
        int size = read.getInt("size",-1);
        tabTilte = new ArrayList<>();
        if(size!=-1){
            for(int i=0;i<=size;i++){
                tabTilte.add(read.getString(i+"",null));
            }
        }
    }

    private void initView(View view){
        tab = view.findViewById(R.id.tab);
        viewPager = view.findViewById(R.id.view_pager);
    }

    private void initViewPager(){
        fragments = new ArrayList<>();
        for(int i=0;i<tabTilte.size();i++){
            fragments.add(NewsListForViewPagerFragment.newInstance(tabTilte.get(i)));
        }
        viewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager(),fragments));
    }

    private void initTabLayout(){
        tab.setupWithViewPager(viewPager);
        //设置可以滑动
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    private void initNewsPortalFragment(){
        //fragmentNavi = new NewsPortalFragment();
//        fragmentNavi = NewsListFragment.newInstance("推荐",false);
//        getChildFragmentManager().beginTransaction().replace(R.id.news_root,fragmentNavi).commitAllowingStateLoss();
    }

}