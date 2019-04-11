package com.lecai.quwen.Bean;

public class User {
    private String u_unionid;
    private String token;
    private String uid;
    private String name;
    private float gold;
    private String head_img_url;

    public User(String u_unionid, String token) {
        this.u_unionid = u_unionid;
        this.token = token;
    }

    public User(String u_unionid, String token, String uid, String name, float gold, String head_img_url) {
        this.u_unionid = u_unionid;
        this.token = token;
        this.uid = uid;
        this.name = name;
        this.gold = gold;
        this.head_img_url = head_img_url;
    }

    public String getHead_img_url() {
        return head_img_url;
    }

    public void setHead_img_url(String head_img_url) {
        this.head_img_url = head_img_url;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getGold() {
        return gold;
    }

    public void setGold(float gold) {
        this.gold = gold;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getU_unionid() {
        return u_unionid;
    }

    public void setU_unionid(String u_unionid) {
        this.u_unionid = u_unionid;
    }
}
