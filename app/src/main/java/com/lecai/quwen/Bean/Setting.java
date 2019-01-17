package com.lecai.quwen.Bean;

public class Setting {

    private boolean isChannleChang = false;
    private int Channle = 1;
    private static Setting instance = null;

    public Setting() {
    }

    public static Setting getInstance(){
        if(instance == null){
            instance = new Setting();
        }
        return instance;
    }

    public boolean isChannleChang() {
        return isChannleChang;
    }

    public void setChannleChang(boolean channleChang) {
        isChannleChang = channleChang;
    }

    public int getChannle() {
        return Channle;
    }

    public void setChannle(int channle) {
        Channle = channle;
    }
}
