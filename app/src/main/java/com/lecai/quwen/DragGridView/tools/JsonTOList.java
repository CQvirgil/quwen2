package com.lecai.quwen.DragGridView.tools;

import com.lecai.quwen.DragGridView.bean.ProvinceItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonTOList {

    public static List<ProvinceItem> toList(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json.trim());
        JSONArray jsonArray = jsonObject.getJSONArray("province_arr");
        List<ProvinceItem> list = new ArrayList<>();
        for(int i=0;i<jsonArray.length();i++){
            JSONObject jsonProvince = jsonArray.getJSONObject(i);
            int province_id = jsonProvince.getInt("province_id");
            String province_name = jsonProvince.getString("province_name");
            list.add(new ProvinceItem(province_id,province_name));
        }
        return list;
    }

}
