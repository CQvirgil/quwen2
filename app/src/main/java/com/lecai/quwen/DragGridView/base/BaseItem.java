package com.lecai.quwen.DragGridView.base;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class BaseItem {

    public abstract JSONObject toJson() throws JSONException;

    public abstract void fromJson(JSONObject jsonObject);
}
