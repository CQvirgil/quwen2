package com.lecai.quwen.Bean;

public class WXUserBean {
    private String nickname;//昵称
    private int sex;//1为男2为女
    private String province;//普通用户个人资料填写的省份
    private String city;//普通用户个人资料填写的城市
    private String headimgurl; //用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空
    private String country;
    private String privilege;//用户特权信息，json数组，如微信沃卡用户为（chinaunicom）
    private String unionid;//用户统一标识。针对一个微信开放平台帐号下的应用，同一用户的unionid是唯一的。
    private int TOKEN;
    private boolean hasSignInToDay = false;
    private int SignInDay = 0;

    public boolean isHasSignInToDay() {
        return hasSignInToDay;
    }

    public void setHasSignInToDay(boolean hasSignInToDay) {
        this.hasSignInToDay = hasSignInToDay;
    }

    public int getSignInDay() {
        return SignInDay;
    }

    public void setSignInDay(int signInDay) {
        SignInDay = signInDay;
    }

    public WXUserBean(String nickname, int sex, String province, String city, String headimgurl, String country, String privilege, String unionid, int TOKEN) {
        this.nickname = nickname;
        this.sex = sex;
        this.province = province;
        this.city = city;
        this.headimgurl = headimgurl;
        this.country = country;
        this.privilege = privilege;
        this.unionid = unionid;
        this.TOKEN = TOKEN;
    }

    public int getTOKEN() {
        return TOKEN;
    }

    public void setTOKEN(int TOKEN) {
        this.TOKEN = TOKEN;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getNickname() {
        return nickname;
    }

    public int getSex() {
        return sex;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public String getCountry() {
        return country;
    }

    public String getPrivilege() {
        return privilege;
    }

    public String getUnionid() {
        return unionid;
    }
}
