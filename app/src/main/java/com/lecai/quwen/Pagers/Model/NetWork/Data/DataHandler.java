package com.lecai.quwen.Pagers.Model.NetWork.Data;

import android.content.SharedPreferences;
import android.util.Log;

import com.lecai.quwen.Pagers.Model.AndroidRX.RxBus;
import com.lecai.quwen.Bean.Team;
import com.lecai.quwen.Bean.User;
import com.lecai.quwen.MyApplication;
import com.lecai.quwen.Pagers.Model.NetWork.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

public class DataHandler implements Consumer<JSONObject> {
    private final String TAG = "DataHandler";
    private static DataHandler instance = null;
    private SharedPreferences read;
    private SharedPreferences.Editor editor;

    private DataHandler() {
        RxBus.getInstance().subscribe(JSONObject.class, this);
    }

    public static DataHandler getInstance() {
        if (instance == null) {
            synchronized (DataHandler.class) {
                instance = new DataHandler();
            }
        }
        return instance;
    }

    @Override
    public void accept(JSONObject jsonObject) throws Exception {
        Log.i(TAG, jsonObject.toString());
        StringBuffer SRxid = new StringBuffer(jsonObject.getString("Rxid"));
        switch (SRxid.toString()) {
            case "45001":
                HandUUIDData(jsonObject);
                break;
            case "45002":
                HandUserData(jsonObject);
                break;
            case "45003":
                HandListData(jsonObject);
                break;
        }
    }

    private void HandUserData(JSONObject jsonObject) throws JSONException {
        JSONObject data = jsonObject.getJSONObject("data");
        User user = new User(
                data.getString("u_unionid"),
                "",
                data.getString("uid"),
                data.getString("name"),
                data.getInt("gold"),data.getString("headimg")
        );
        MyApplication.getInstance().setU_unionid(data.getString("u_unionid"));
        Data.getInstance().setUser(user);
    }

    private void HandUUIDData(JSONObject json) throws JSONException {
        MyApplication.getInstance().setAccess_token(json.getString("access_token"));
        MyApplication.getInstance().setRefresh_token(json.getString("refresh_token"));
        MyApplication.getInstance().setU_unionid(json.getString("u_unionid"));
        Data.getInstance().setAccess_token(json.getString("access_token"));
        Data.getInstance().setRefresh_token(json.getString("refresh_token"));
        Data.getInstance().setU_unionid(json.getString("u_unionid"));
        HttpRequest.getInstance().getUser();
    }

    private void HandListData(JSONObject jsonObject) throws JSONException {
        JSONObject json_teamlist = jsonObject;
        List<Team> sub_team = new ArrayList<>(),dom_team = new ArrayList<>();
        if(json_teamlist.getInt("return_code") == 1){
            JSONObject json_teamlist_data = json_teamlist.getJSONObject("data");
            if(json_teamlist_data.getInt("sub_count") > 0){
                for(int i=0;i<json_teamlist_data.getJSONArray("sub_team").length();i++){
                    JSONObject json_team = (JSONObject) json_teamlist_data.getJSONArray("sub_team").get(i);
                    String t_unionid = json_team.getString("t_unionid");
                    String name = json_team.getString("name");
                    String tid = json_team.getString("tid");
                    int number = json_team.getInt("number");
                    sub_team.add(new Team(t_unionid,name,tid,number));
                }
                Data.getInstance().setSub_team(sub_team);
            }
            if(json_teamlist_data.getInt("dom_count") >= 0){
                for(int i=0;i<json_teamlist_data.getJSONArray("dom_team").length();i++){
                    JSONObject json_team = (JSONObject) json_teamlist_data.getJSONArray("dom_team").get(i);
                    String t_unionid = json_team.getString("t_unionid");
                    String name = json_team.getString("name");
                    String tid = json_team.getString("tid");
                    int number = json_team.getInt("number");
                    dom_team.add(new Team(t_unionid,name,tid,number));
                }
                Data.getInstance().setDom_team(dom_team);
            }
        }
    }
}
