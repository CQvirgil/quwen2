package com.lecai.quwen.Pagers.Model.NetWork;

import android.content.SharedPreferences;

import com.lecai.quwen.Pagers.Model.AndroidRX.Rxid;
import com.lecai.quwen.MyApplication;
import com.lecai.quwen.Pagers.Model.NetWork.Data.Data;

import org.json.JSONException;
import org.json.JSONObject;

public class HttpRequest {
    private static HttpRequest instance = null;
    private String server_url = "http://www.lecaigogo.com:4999/api/v1";

    private HttpRequest() {
    }

    public static HttpRequest getInstance() {
        if (instance == null) {
            synchronized (HttpRequest.class) {
                instance = new HttpRequest();
            }
        }
        return instance;
    }

    /*
     * 获取用户信息
     * */
    public void getUser() throws JSONException {
        String url = "http://www.lecaigogo.com:4999/api/v1/user/user_info";
        JSONObject uuid = new JSONObject();
        uuid.put("u_unionid", MyApplication.getInstance().getU_unionid());
        Client.getInstance().PostServerJ(url, uuid, Rxid.GET_USER);
    }

    /*
     * 重置access token
     * */
    public void resetAccessToken(SharedPreferences read) throws JSONException {
        String url = server_url + "/auth/refresh";
        JSONObject json_post = new JSONObject();
        json_post.put("refresh_token", read.getString("refresh_token", null));
        Client.getInstance().PostServerJ(url, json_post, Rxid.RESET_ACCESS_TOKEN);
    }

    /*
     * 获取用户唯一标识
     * code 授权登陆时回传的code
     * */
    public void getUserID(String code) {
        String url = server_url + "/user/wxlogin";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("code", code.toString());
            Client.getInstance().PostServerJ(url, jsonObject, Rxid.GET_UUID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getTokens(String access_token) throws JSONException {
        String url = server_url + "/auth/tokens";
        JSONObject json_post = new JSONObject();
        json_post.put("access_token", access_token);
        Client.getInstance().PostServerJ(url, json_post, Rxid.GET_TOKENS);
    }

    public void getTeamList() throws JSONException {
        String url =  server_url + "/team/team_list";
        JSONObject jsonObject = new JSONObject();
        if (MyApplication.getInstance() != null) {
            jsonObject.put("u_unionid", Data.getInstance().getU_unionid());
        }
        Client.getInstance().PostServerJ(url,jsonObject,Rxid.GET_TEAM_LIST);
    }
}
