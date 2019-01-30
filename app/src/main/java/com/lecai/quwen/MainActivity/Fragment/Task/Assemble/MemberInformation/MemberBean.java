package com.lecai.quwen.MainActivity.Fragment.Task.Assemble.MemberInformation;

public class MemberBean {
    private String HeadImgURL;
    private String Name;
    private int ID;
    private int QYFE;

    public MemberBean(String headImgURL, String name, int ID, int QYFE) {
        HeadImgURL = headImgURL;
        Name = name;
        this.ID = ID;
        this.QYFE = QYFE;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getHeadImgURL() {
        return HeadImgURL;
    }

    public void setHeadImgURL(String headImgURL) {
        HeadImgURL = headImgURL;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getQYFE() {
        return QYFE;
    }

    public void setQYFE(int QYFE) {
        this.QYFE = QYFE;
    }
}
