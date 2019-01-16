package com.lecai.quwen.DragGridView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lecai.quwen.DragGridView.bean.ProvinceItem;
import com.lecai.quwen.DragGridView.draggridview.DragAdapter;
import com.lecai.quwen.DragGridView.draggridview.DragGridView;
import com.lecai.quwen.DragGridView.model.ProvinceModel;
import com.lecai.quwen.DragGridView.tools.Constant;
import com.lecai.quwen.MainActivity.Fragment.HomepageFragment;
import com.lecai.quwen.R;

import java.util.ArrayList;
import java.util.List;

public class DragGridFragment extends Fragment implements DragAdapter.changeListener {

    private DragGridView gridView;

    private List<ProvinceItem> items = new ArrayList<ProvinceItem>();

    private SharedPreferences mShared;
    private SharedPreferences.Editor mEditor;
    private Button button;
    private  DragAdapter dragAdapter;
    private boolean isclick = false;
    private Button editor_item;

    ProvinceModel model;

    public DragGridFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DragGridFragment newInstance() {
        DragGridFragment fragment = new DragGridFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drag_grid, container, false);
        editor_item = view.findViewById(R.id.set);
        gridView = view.findViewById(R.id.userGridView);
        init();
        return view;
    }

    private void init() {
        mShared =  getContext().getSharedPreferences(Constant.USER, 0);
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

        gridView.setAdapter(dragAdapter);
        dragAdapter.setListener(this);

        editor_item.setOnClickListener(new View.OnClickListener() {
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
        //重新排序后出发该事件
        HomepageFragment.isChang = true;
    }

    @Override
    public void setCurrentPosition() {

    }

    @Override
    public void onClearClick(int position) {

    }
}
