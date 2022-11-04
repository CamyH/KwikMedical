package com.cameron.kwikmedical.Business;

public class Hospital {

    public Hospital(String name, String address, String postCode) {
        this.name = name;
        this.address = address;
        this.postCode = postCode;
    }

    private String name;
    private String address;
    private String postCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
}
