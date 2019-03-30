package com.lecai.quwen.Bean;

public class Message {
    private String sub_unionid;
    private String sub_name;
    private String text;

    public Message(String sub_unionid, String sub_name, String text) {
        this.sub_unionid = sub_unionid;
        this.sub_name = sub_name;
        this.text = text;
    }

    public String getSub_unionid() {
        return sub_unionid;
    }

    public void setSub_unionid(String sub_unionid) {
        this.sub_unionid = sub_unionid;
    }

    public String getSub_name() {
        return sub_name;
    }

    public void setSub_name(String sub_name) {
        this.sub_name = sub_name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
