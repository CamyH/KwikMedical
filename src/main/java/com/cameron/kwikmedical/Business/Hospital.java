package com.cameron.kwikmedical.Business;

/**
 * Class for setting the Hospital details
 */
public class Hospital {
    public Hospital(String name, String address) {
        this.name = name;
        this.address = address;
    }
    private String name;
    private String address;

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

}
