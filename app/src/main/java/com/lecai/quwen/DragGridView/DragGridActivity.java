package com.lecai.quwen.DragGridView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.lecai.quwen.Bean.Setting;
import com.lecai.quwen.DragGridView.bean.ProvinceItem;
import com.lecai.quwen.DragGridView.draggridview.DragAdapter;
import com.lecai.quwen.DragGridView.draggridview.DragGridView;
import com.lecai.quwen.DragGridView.model.ProvinceModel;
import com.lecai.quwen.DragGridView.tools.Constant;
import com.lecai.quwen.R;

import java.util.ArrayList;
import java.util.List;

import static android.view.KeyEvent.KEYCODE_BACK;

public class DragGridActivity extends Activity implements DragAdapter.changeListener {

    private DragGridView gridView;
    private GridView gridView_channle;

    private List<ProvinceItem> items = new ArrayList<ProvinceItem>();
    private List<ProvinceItem> channles = new ArrayList<ProvinceItem>();
    private mAdapter dragAdapter_channles;

    private SharedPreferences mShared;
    private SharedPreferences.Editor mEditor;
    private  DragAdapter dragAdapter;
    private boolean isclick = false;
    Button btn_editor_cannle;
    public static boolean EDITOR_STATE = false;

    ProvinceModel model;

    public static Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_grid);
        init();
        initChannles();
    }

    @SuppressLint("HandlerLeak")
    private void initHandler(){
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what == 2001){
                    int pos = (int) msg.obj;
                    Toast.makeText(DragGridActivity.this, pos+"", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    private void initChannles(){
        channles.add(new ProvinceItem(1, "推荐"));
        channles.add(new ProvinceItem(2, "热点"));
        channles.add(new ProvinceItem(3, "科技"));
        channles.add(new ProvinceItem(4, "娱乐"));
        channles.add(new ProvinceItem(5, "汽车"));
        channles.add(new ProvinceItem(6, "旅游"));
        channles.add(new ProvinceItem(7, "情感"));
        for(int i=0;i<items.size();i++){
            //Log.i("initChannles",items.get(i).getId()+" "+items.get(i).getName());
            for(int n=0;n<channles.size();n++){
                //Log.i("initChannles",channles.get(i).getId()+" "+channles.get(i).getName());
                if(items.get(i).getName().toString().equals(channles.get(n).getName())){
                    channles.remove(n);
                }
            }
        }

        initgridView_channle();
    }

    private void initgridView_channle(){
        gridView_channle = findViewById(R.id.The_rest_cannle);
        dragAdapter_channles = new mAdapter(this,channles);
        gridView_channle.setAdapter(dragAdapter_channles);
        gridView_channle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = view.findViewById(R.id.title);
                String text = textView.getText().toString();
                String name = text.substring(1);
                dragAdapter.addItem(channles.get(position));
                dragAdapter_channles.removePosition(position);
                Setting.getInstance().setChannleChang(false);
                //Toast.makeText(DragGridActivity.this, name, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init() {
        mShared = getSharedPreferences(Constant.USER, 0);
        mEditor = mShared.edit();
        model = new ProvinceModel(this);
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

    private void initData() {
        items.add(new ProvinceItem(1, "推荐"));
        items.add(new ProvinceItem(2, "热点"));
        items.add(new ProvinceItem(3, "科技"));
        items.add(new ProvinceItem(4, "娱乐"));
        items.add(new ProvinceItem(5, "汽车"));
        items.add(new ProvinceItem(6, "旅游"));
    }

    private void initView() {
        dragAdapter = new DragAdapter(this,items);
        gridView = findViewById(R.id.userGridView);
        gridView.setAdapter(dragAdapter);
        dragAdapter.setListener(this);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(!EDITOR_STATE){
                    Setting.getInstance().setChannleChang(false);
                    Setting.getInstance().setChannle(position);
                    finish();
                }
            }
        });

        btn_editor_cannle = findViewById(R.id.set);
        btn_editor_cannle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isclick){
                    dragAdapter.setCLEAR_ISVISIBLE(false);
                    dragAdapter.showAll();
                    isclick = false;
                    btn_editor_cannle.setText("编辑");
                    EDITOR_STATE = false;
                }else{
                    dragAdapter.setCLEAR_ISVISIBLE(true);
                    dragAdapter.showAll();
                    isclick = true;
                    btn_editor_cannle.setText("完成");
                    EDITOR_STATE = true;
                }
            }
        });
    }

    @Override
    public void exchangeOtherAdapter(List<ProvinceItem> data, int position) {
        Setting.getInstance().setChannleChang(false);
    }

    @Override
    public void setCurrentPosition() {

    }

    @Override
    public void onClearClick(int position) {
        dragAdapter_channles.addItem(items.get(position));
        dragAdapter.removePosition(position);
        Setting.getInstance().setChannle(1);
        Setting.getInstance().setChannleChang(false);
    }


    public void Close(View view) {
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(EDITOR_STATE&&keyCode == KEYCODE_BACK){
            dragAdapter.setCLEAR_ISVISIBLE(false);
            dragAdapter.showAll();
            isclick = false;
            btn_editor_cannle.setText("编辑");
            EDITOR_STATE = false;
            return true;
        }else{
            return super.onKeyDown(keyCode, event);
        }
    }
}
