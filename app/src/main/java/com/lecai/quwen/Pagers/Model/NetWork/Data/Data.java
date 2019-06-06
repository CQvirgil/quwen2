package com.lecai.quwen.Pagers.Model.NetWork.Data;

import com.lecai.quwen.Bean.Apprentice;
import com.lecai.quwen.Bean.Message;
import com.lecai.quwen.Bean.Record;
import com.lecai.quwen.Bean.Team;
import com.lecai.quwen.Bean.User;
import com.lecai.quwen.Pagers.View.Activity.AssembleActivity;

import java.util.List;

public class Data {
    private static Data instance = null;
    private User user = null;//用户信息
    private Team team = null;//拼团数据
    private Message message = null;//消息数据
    private Record record = null;
    private Apprentice apprentice = null;
    private String access_token = null;
    private String refresh_token = null;
    private String u_unionid = null;
    private List<Team> sub_team = null,dom_team = null;

    private Data(){
    }

    public static Data getInstance(){
        if(instance == null){
            synchronized (Data.class){
                instance = new Data();
            }
        }
        return instance;
    }

    public List<Team> getSub_team() {
        return sub_team;
    }

    public void setSub_team(List<Team> sub_team) {
        this.sub_team = sub_team;
        AssembleActivity.handler.sendEmptyMessage(2001);
    }

    public List<Team> getDom_team() {
        return dom_team;
    }

    public void setDom_team(List<Team> dom_team) {
        this.dom_team = dom_team;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getU_unionid() {
        return u_unionid;
    }

    public void setU_unionid(String u_unionid) {
        this.u_unionid = u_unionid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    public Apprentice getApprentice() {
        return apprentice;
    }

    public void setApprentice(Apprentice apprentice) {
        this.apprentice = apprentice;
    }
}
