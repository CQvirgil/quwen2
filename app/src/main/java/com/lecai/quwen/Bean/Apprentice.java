package com.lecai.quwen.Bean;

public class Apprentice {
    private String sub_unionid;
    private String uid;
    private String name;
    private int gold;

    public Apprentice(String sub_unionid, String uid, String name, int gold) {
        this.sub_unionid = sub_unionid;
        this.uid = uid;
        this.name = name;
        this.gold = gold;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSub_unionid() {
        return sub_unionid;
    }

    public void setSub_unionid(String sub_unionid) {
        this.sub_unionid = sub_unionid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
}
