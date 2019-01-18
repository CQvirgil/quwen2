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

import com.lecai.quwen.Bean.Setting;
import com.lecai.quwen.DragGridView.bean.ProvinceItem;
import com.lecai.quwen.DragGridView.tools.JsonTOList;
import com.lecai.quwen.R;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;


@SuppressLint("ValidFragment")
public class HomepageFragment extends Fragment {
    private Context context;
    private ViewPager viewPager;
    private TabLayout tab;
    public static List<ProvinceItem> tabTilte;
    private List<ChannelFragment> fragments;
    private SharedPreferences read;
    private Button btn;
    private ViewPagerAdapter viewPagerAdapter;
    private int tabsize = 0;
    public static boolean isChang = true;
    private static volatile HomepageFragment instance;

    @SuppressLint("ValidFragment")
    public HomepageFragment(Context context) {
        this.context = context;
    }

    public static HomepageFragment newInstance(Context context) {
        if(instance == null){
            instance = new HomepageFragment(context);
        }
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        read = getContext().getSharedPreferences("user",MODE_PRIVATE);
        Log.i("asdasdasdasd","onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        initTabLayout();

        try {
            initTabTitle();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        initViewPager();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(!Setting.getInstance().isChannleChang()){
            try {
                initTabTitle();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            initViewPager();
            Setting.getInstance().setChannleChang(true);
            if(tab!=null&&Setting.getInstance()!=null){
                tab.getTabAt(Setting.getInstance().getChannle()).select();
            }
        }

    }

    private void initTabTitle() throws JSONException {
        String province = read.getString("province",null);
        if(province == null){
            tabTilte = new ArrayList<ProvinceItem>();
            initTabData();
        }else{
            tabTilte = JsonTOList.toList(province);
            if(tabsize == 0){
                tabsize = tabTilte.size();
            }
        }

    }

    private void initTabData() {
        tabTilte.add(new ProvinceItem(1, "推荐"));
        tabTilte.add(new ProvinceItem(2, "热点"));
        tabTilte.add(new ProvinceItem(3, "科技"));
        tabTilte.add(new ProvinceItem(4, "娱乐"));
        tabTilte.add(new ProvinceItem(5, "汽车"));
        tabTilte.add(new ProvinceItem(6, "旅游"));
    }

    private void initView(View view){
        tab = view.findViewById(R.id.tab);
        viewPager = view.findViewById(R.id.view_pager);
    }

    private void initViewPager(){
        fragments = new ArrayList<>();
        for(int i=0;i<tabTilte.size();i++){
            fragments.add(ChannelFragment.newInstance(tabTilte.get(i).getName()));
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