package com.amirhosseinemadi.myAccountant.model;


public class Cheque {

    private String name;
    private Long value;
    private Long date;
    private Long alarm;
    private String account;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Long getAlarm() {
        return alarm;
    }

    public void setAlarm(Long alarm) {
        this.alarm = alarm;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
