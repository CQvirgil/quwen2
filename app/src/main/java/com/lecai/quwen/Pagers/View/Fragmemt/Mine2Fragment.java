package com.lecai.quwen.Pagers.View.Fragmemt;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lecai.quwen.Bean.Mine2Message;
import com.lecai.quwen.Pagers.View.Adapter.MessagesAdapter;
import com.lecai.quwen.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Mine2Fragment extends Fragment {
    private RecyclerView messages;

    public Mine2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mine2, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        getMessages(view);
        setStatusBar();
    }

    @SuppressLint("NewApi")
    private void setStatusBar(){
        //getActivity().getWindow().setStatusBarColor(Color.WHITE);
    }

    private void getMessages(View view) {
        messages = view.findViewById(R.id.fgm_mine2_messages);
        List<Mine2Message> item = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            item.add(new Mine2Message("未读消息标题内容比如200金币已兑换成功，将在24小时内到账，标题内容最多2行…", "今天  13:00"));
        }
        MessagesAdapter adapter = new MessagesAdapter(item);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        messages.setLayoutManager(layoutManager);
        messages.setAdapter(adapter);
    }

}
