package com.lecai.quwen.Bean;

public class Team {
    private String t_unionid;
    private String name;
    private String tid;
    private int number;

    public Team(String t_unionid, String name, String tid, int number) {
        this.t_unionid = t_unionid;
        this.name = name;
        this.tid = tid;
        this.number = number;
    }

    public String getT_unionid() {
        return t_unionid;
    }

    public void setT_unionid(String t_unionid) {
        this.t_unionid = t_unionid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
