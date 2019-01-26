package com.lecai.quwen.Bean;

public class   HandlerMsg {
    private static HandlerMsg instance;
    private int main_click_home ;
    private int SET_fgm_Mine_LL_user_VISIABLE ;
    private int SET_USER_ICON ;

    public HandlerMsg() {
        main_click_home = 2100;
        SET_fgm_Mine_LL_user_VISIABLE = 1001;
        SET_USER_ICON = 1002;
    }

    public static HandlerMsg getInstance(){
        if(instance == null){
            instance = new HandlerMsg();
        }
        return instance;
    }

    public int getMain_click_home() {
        return main_click_home;
    }

    public int getSET_fgm_Mine_LL_user_VISIABLE() {
        return SET_fgm_Mine_LL_user_VISIABLE;
    }

    public int getSET_USER_ICON() {
        return SET_USER_ICON;
    }
}
