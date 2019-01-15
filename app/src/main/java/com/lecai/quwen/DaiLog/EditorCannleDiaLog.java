package com.lecai.quwen.DaiLog;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lecai.quwen.DragGridView.bean.ProvinceItem;
import com.lecai.quwen.DragGridView.draggridview.DragAdapter;
import com.lecai.quwen.DragGridView.draggridview.DragAdapter.changeListener;
import com.lecai.quwen.DragGridView.draggridview.DragGridView;
import com.lecai.quwen.DragGridView.model.ProvinceModel;
import com.lecai.quwen.DragGridView.tools.Constant;
import com.lecai.quwen.R;

import java.util.ArrayList;
import java.util.List;

public class EditorCannleDiaLog extends BaseDiaLog implements changeListener {

    private DragGridView gridView;

    private List<ProvinceItem> items = new ArrayList<ProvinceItem>();

    private SharedPreferences mShared;
    private SharedPreferences.Editor mEditor;
    private Button button;
    private DragAdapter dragAdapter;
    private boolean isclick = false;

    ProvinceModel model;

    public EditorCannleDiaLog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_drag_grid);
        init();
    }

    private void init() {
        mShared = getContext().getSharedPreferences(Constant.USER, 0);
        mEditor = mShared.edit();
        model = new ProvinceModel(getContext());
        if (mShared.getBoolean(Constant.IS_FIRST,true)){
            initData();
            mEditor.putBoolean(Constant.IS_FIRST, false);
            mEditor.commit();
        }else {
            model.getProvinceListFromCache();
            if (null != model.list && model.list.size()>0){
                items = model.list;
            }
//            initData();
        }
//        initData();
        initView();
    }

    private void initView() {
        dragAdapter = new DragAdapter(getContext(),items);
        gridView =  findViewById(R.id.userGridView);
        gridView.setAdapter(dragAdapter);
        dragAdapter.setListener(this);
        button = findViewById(R.id.add);
        Button set = findViewById(R.id.set);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isclick){
                    dragAdapter.setCLEAR_ISVISIBLE(false);
                    dragAdapter.showAll();
                    isclick = false;
                }else{
                    dragAdapter.setCLEAR_ISVISIBLE(true);
                    dragAdapter.showAll();
                    isclick = true;
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    private void initData() {
        items.add(new ProvinceItem(1, "推荐"));
        items.add(new ProvinceItem(2, "热点"));
        items.add(new ProvinceItem(3, "科技"));
        items.add(new ProvinceItem(4, "娱乐"));
        items.add(new ProvinceItem(5, "汽车"));
        items.add(new ProvinceItem(6, "旅游"));
    }

    @Override
    public void exchangeOtherAdapter(List<ProvinceItem> data, int position) {

    }

    @Override
    public void setCurrentPosition() {

    }
}
