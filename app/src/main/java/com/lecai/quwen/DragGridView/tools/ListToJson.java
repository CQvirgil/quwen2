package com.lecai.quwen.DragGridView.tools;

import android.support.annotation.NonNull;

import com.lecai.quwen.DragGridView.base.BaseItem;
import com.lecai.quwen.DragGridView.bean.ProvinceItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ListToJson {
    public static JSONObject toJson(@NonNull List<ProvinceItem> list){
        if (list.size()>0){
            try{
                JSONObject jsonObject = new JSONObject();
                JSONArray arr = new JSONArray();
                for (BaseItem item: list) {
                    JSONObject jsonItem = item.toJson();
                    arr.put(jsonItem);
                }
                jsonObject.put(Constant.PROVINCE_ARR,arr);
                return jsonObject;
            }catch (JSONException e){
                e.printStackTrace();
            }

        }

        return null;
    }
}
