package com.lecai.quwen.MainActivity.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lecai.quwen.R;
import com.yidian.newssdk.exportui.NewsPortalFragment;


@SuppressLint("ValidFragment")
public class HomepageFragment extends Fragment {
    private Context context;
    private NewsPortalFragment fragmentNavi;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initNewsPortalFragment();
        return view;
    }

    private void initNewsPortalFragment(){
        fragmentNavi = new NewsPortalFragment();
        getChildFragmentManager().beginTransaction().replace(R.id.news_root,fragmentNavi).commitNowAllowingStateLoss();
    }

}