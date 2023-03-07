package com.example.darajaapi.payload;

public class StkPayload {
    private String phoneNumber;
    private String amount;
    private StkInfo info;

    public StkInfo getInfo() {
        return info;
    }

    public void setInfo(StkInfo info) {
        this.info = info;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
