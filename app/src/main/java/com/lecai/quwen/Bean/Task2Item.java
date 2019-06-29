package com.lecai.quwen.Bean;

public class Task2Item {
    private String title;
    private float unit;
    private float unit_all;
    private float value;
    private float value_all;

    public Task2Item(String title, float unit, float unit_all, float value, float value_all) {
        this.title = title;
        this.unit = unit;
        this.unit_all = unit_all;
        this.value = value;
        this.value_all = value_all;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getUnit() {
        return unit;
    }

    public void setUnit(float unit) {
        this.unit = unit;
    }

    public float getUnit_all() {
        return unit_all;
    }

    public void setUnit_all(float unit_all) {
        this.unit_all = unit_all;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getValue_all() {
        return value_all;
    }

    public void setValue_all(float value_all) {
        this.value_all = value_all;
    }
}
