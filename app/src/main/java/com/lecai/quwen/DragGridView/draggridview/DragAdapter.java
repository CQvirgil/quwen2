package com.lecai.quwen.DragGridView.draggridview;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lecai.quwen.DragGridView.DragGridActivity;
import com.lecai.quwen.DragGridView.base.BaseDragAdapter;
import com.lecai.quwen.DragGridView.base.BaseItem;
import com.lecai.quwen.DragGridView.bean.ProvinceItem;
import com.lecai.quwen.DragGridView.tools.Constant;
import com.lecai.quwen.DragGridView.tools.ListToJson;
import com.lecai.quwen.R;

import java.util.List;

public class DragAdapter extends BaseDragAdapter {

    private static final String TAG = "DragAdapter";

    private Context context;
    private int dropPosition = -1;
    private List<ProvinceItem> provinceList;
    private SharedPreferences mShared;
    private SharedPreferences.Editor mEditor;
    private ProvinceItem selectItem;
    private boolean CLEAR_ISVISIBLE = false;
    private int TYPE = 1;
    public static int TYPE_1 = 1,TYPE_2 = 2;

    private View view;

    private int mHidePosition = -1;

    public DragAdapter(Context context,List<ProvinceItem> provinceList){
        this.context = context;
        this.provinceList = provinceList;
        mShared = context.getSharedPreferences(Constant.USER,0);
        mEditor = mShared.edit();
        selectItem = provinceList.get(0);
    }

    public int getTYPE() {
        return TYPE;
    }

    public void setTYPE(int TYPE) {
        this.TYPE = TYPE;
    }

    @Override
    public int getCount() {
        return provinceList == null ? 0 : provinceList.size();
    }

    @Override
    public Object getItem(int position) {
        if (null != provinceList && provinceList.size() != 0){
            return provinceList.get(position);
        }
        return null;
    }

    public String getItemName(int position){
        if (null != provinceList && provinceList.size() != 0){
            return provinceList.get(position).getName();
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setCLEAR_ISVISIBLE(boolean clear_isvisible){
        this.CLEAR_ISVISIBLE = clear_isvisible;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO: 16-3-26 控件的ｂｕｇ 不能使用convertView and holder
        if(TYPE == TYPE_1){
            view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
            TextView textView = (TextView) view.findViewById(R.id.title);
            ImageView clear = view.findViewById(R.id.clear);

            final ProvinceItem item = provinceList.get(position);
            textView.setText(item.getName()+"");
            if (dropPosition == position){
                view.setVisibility(View.GONE);
            }
            if (selectItem.getId() == provinceList.get(position).getId()){
                view.setBackgroundColor(Color.parseColor("#fbfbfb"));
                textView.setTextColor(Color.parseColor("#ff604f"));
            }else {
                view.setBackgroundColor(Color.parseColor("#ffffff"));
                textView.setTextColor(Color.parseColor("#464646"));

                if(CLEAR_ISVISIBLE&&position>1){
                    clear.setVisibility(View.VISIBLE);
                    clear.setClickable(true);
                    clear.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            listener.onClearClick(position);
                        }
                    });
                }else{
                    clear.setClickable(false);
                }
            }
        }else if(TYPE == TYPE_2){
            view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
            final ProvinceItem item = provinceList.get(position);
            TextView textView = view.findViewById(R.id.title);
            textView.setTextColor(Color.parseColor("#7f7f7f"));
            textView.setText("+"+item.getName());
        }


        return view;
//        final ViewHolder holder ;
//        if (null == convertView){
//            convertView = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
//            holder = new ViewHolder();
//            holder.textView = (TextView) convertView.findViewById(R.id.title);
//            convertView.setTag(holder);
//        }else {
//            holder = (ViewHolder) convertView.getTag();
//        }
//        final ProvinceItem item = provinceList.get(position);
//        holder.textView.setText(item.getName()+"");
//        if (mHidePosition == position){
//            convertView.setVisibility(View.INVISIBLE);
//        }else {
//            convertView.setVisibility(View.VISIBLE);
//        }
//        if (selectItem.getId() == provinceList.get(position).getId()){
//            convertView.setBackgroundColor(Color.parseColor("#fbfbfb"));
//            holder.textView.setTextColor(Color.parseColor("#ff604f"));
//        }else {
//            convertView.setBackgroundColor(Color.parseColor("#ffffff"));
//            holder.textView.setTextColor(Color.parseColor("#464646"));
//        }
//        return convertView;
    }

    @Override
    public void addItem(BaseItem item) {
        provinceList.add((ProvinceItem) item);
        mEditor.putString(Constant.PROVINCE, ListToJson.toJson(provinceList).toString());
        mEditor.commit();
        notifyDataSetChanged();
    }

    @Override
    public void exchange(int dragPosition, int dropPosition) {
        // TODO: 16-3-22 互换位置
        this.dropPosition = dropPosition;
        ProvinceItem dragItem = (ProvinceItem) getItem(dragPosition);
        if (dragPosition < dropPosition) {
            provinceList.add(dropPosition + 1, dragItem);
            provinceList.remove(dragPosition);
        } else {
            provinceList.add(dropPosition, dragItem);
            provinceList.remove(dragPosition + 1);
        }
        this.mHidePosition = dropPosition;

        mEditor.putString(Constant.PROVINCE, ListToJson.toJson(provinceList).toString());
        mEditor.commit();
        notifyDataSetChanged();
    }

    @Override
    public void removeItem(BaseItem item) {
        if (provinceList.contains((ProvinceItem)item)){
            provinceList.remove((ProvinceItem) item);
            dropPosition = -1;
            notifyDataSetChanged();
        }
    }

    @Override
    public void removePosition(int position) {
        if (position >=0 && position<provinceList.size()){
            provinceList.remove(position);
            mEditor.putString(Constant.PROVINCE, ListToJson.toJson(provinceList).toString());
            mEditor.commit();
            notifyDataSetChanged();
        }
    }

    @Override
    public void dragEnd() {
        // TODO: 16-3-26 拖动完成的回调
        int position = 0;
        for (int i = 0; i < provinceList.size(); i++) {
            if (selectItem.getId()==provinceList.get(i).getId()){
                position = i;
                break;
            }
        }

        this.dropPosition = -1;
        if (null != listener){
            listener.exchangeOtherAdapter(provinceList,position);
        }
        this.mHidePosition = -1 ;
        notifyDataSetChanged();
    }

    private changeListener listener;

    public void setListener(changeListener listener){
        this.listener = listener;
    }

    public interface changeListener{

        public void exchangeOtherAdapter(List<ProvinceItem> data, int position);

        public void setCurrentPosition();
        public void onClearClick(int position);
    }

    private class ViewHolder{
        private View view;
        private TextView textView;
    }

    @Override
    public void hidePosition(int position) {
        this.mHidePosition = position;
        notifyDataSetChanged();
    }

    @Override
    public void showAll() {
        this.mHidePosition = -1;
        notifyDataSetChanged();
    }
}
