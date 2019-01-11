package com.lecai.quwen.MainActivity.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lecai.quwen.R;
import com.yidian.newssdk.exportui.NewsListFragment;

@SuppressLint("ValidFragment")
public class ChannelFragment extends Fragment {
    private NewsListFragment fragment;
    private Button btn_SX;
    private final String Tag = "ChannelFragmentTag";
    private String channelName;

    @SuppressLint("ValidFragment")
    public ChannelFragment(String channelName) {
        //this.fragment = NewsListFragment.newInstance(channelName,false);
        this.channelName = channelName;
    }

    public static ChannelFragment newInstance(String channelName) {
        ChannelFragment fragment = new ChannelFragment(channelName);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_channel, container, false);
        btn_SX = view.findViewById(R.id.btn_SX);
        btn_SX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.refreshCurrentChannel();
            }
        });
        Log.i(Tag,channelName+"onCreateView");
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.i(Tag,channelName+"onDetach");
        fragment = NewsListFragment.newInstance(channelName,false);
        getChildFragmentManager().beginTransaction().replace(R.id.channel_root,fragment).commitNowAllowingStateLoss();
    }

    @Override
    public void onStop() {
        super.onStop();
        fragment = null;
        Log.i(Tag,channelName+"onStop");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(Tag,channelName+"onPause");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(Tag,channelName+"onAttach");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(Tag,channelName+"onStart");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(Tag,channelName+"onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(Tag,channelName+"onDetach");
    }

}
