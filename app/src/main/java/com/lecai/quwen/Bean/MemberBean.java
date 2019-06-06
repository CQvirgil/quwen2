package com.lecai.quwen.Bean;

public class MemberBean {
   private String m_name;
   private String m_unionid;
   private String m_uid;
   private String m_headimg;
   private int gold;

    public MemberBean(String m_name, String m_unionid) {
        this.m_name = m_name;
        this.m_unionid = m_unionid;
    }


    public MemberBean(String m_name, String m_unionid, String m_uid, String m_headimg, int gold) {
        this.m_name = m_name;
        this.m_unionid = m_unionid;
        this.m_uid = m_uid;
        this.m_headimg = m_headimg;
        this.gold = gold;
    }

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public String getM_unionid() {
        return m_unionid;
    }

    public void setM_unionid(String m_unionid) {
        this.m_unionid = m_unionid;
    }

    public String getM_uid() {
        return m_uid;
    }

    public void setM_uid(String m_uid) {
        this.m_uid = m_uid;
    }

    public String getM_headimg() {
        return m_headimg;
    }

    public void setM_headimg(String m_headimg) {
        this.m_headimg = m_headimg;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
}
