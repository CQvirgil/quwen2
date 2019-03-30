package com.lecai.quwen.MainActivity.Fragment.Task.Mentor.BindingMaster;

public class MasterBean {
    private String uid;
    private String name;
    private String u_unionid;
    private int is_follow;

    public MasterBean(String uid, String name, String u_unionid, int is_follow) {
        this.uid = uid;
        this.name = name;
        this.u_unionid = u_unionid;
        this.is_follow = is_follow;
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

    public String getU_unionid() {
        return u_unionid;
    }

    public void setU_unionid(String u_unionid) {
        this.u_unionid = u_unionid;
    }

    public int getIs_follow() {
        return is_follow;
    }

    public void setIs_follow(int is_follow) {
        this.is_follow = is_follow;
    }
}
