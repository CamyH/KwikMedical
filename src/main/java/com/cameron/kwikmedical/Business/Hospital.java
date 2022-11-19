package com.cameron.kwikmedical.Business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 * Class for setting the Hospital details
 */
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
