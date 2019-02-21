package com.lecai.quwen.Bean;

public class Record {

    private int status;//申请状态1=正在申请，2=正在成功，3=申请失败
    private float money;
    private int number;
    private String date;

    public Record(int status, float money, int number, String date) {
        this.status = status;
        this.money = money;
        this.number = number;
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

