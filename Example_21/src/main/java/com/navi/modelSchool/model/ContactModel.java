package com.navi.modelSchool.model;

public class ContactModel {
    String name;
    String mobileNumber;
    String emil;
    String sub;
    String msg;

    public ContactModel(String name, String mobileNumber, String emil, String sub, String msg) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.emil = emil;
        this.sub = sub;
        this.msg = msg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmil() {
        return emil;
    }

    public void setEmil(String emil) {
        this.emil = emil;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
