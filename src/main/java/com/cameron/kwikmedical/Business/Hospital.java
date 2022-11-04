package com.cameron.kwikmedical.Business;

import java.util.ArrayList;

public class Hospital {

    public Hospital(String name, String address, String postCode) {
        this.name = name;
        this.address = address;
        this.postCode = postCode;
    }

    private ArrayList<Hospital> hospitalList;
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

    public ArrayList<Hospital> getHospitalList() {
        return hospitalList;
    }

    public void setHospitalList(Hospital hospitalDetails) {
        this.hospitalList.add(hospitalDetails);
    }

    public String LocateNearestHospital(String postCode, ArrayList<Hospital> hospitalList) {
        // Not finished
        // Currently requires a hospital to be in the same post code
        // Will add in when I get the program in a more finished state
        String closestHospital = "";
        String postCodeIdentifier;
        boolean doubleDigitCode = true;

        if (postCode.length() == 7)
            postCodeIdentifier = postCode.substring(0, 3);
        else {
            postCodeIdentifier = postCode.substring(0, 2);
            doubleDigitCode = false;
        }
        for (Hospital hospital : hospitalList) {
            if (doubleDigitCode) {
                if (postCodeIdentifier.equals(hospital.getPostCode().substring(0, 3)))
                    closestHospital = hospital.getName();
            } else {
                if (postCodeIdentifier.equals(hospital.getPostCode().substring(0, 2)))
                    closestHospital = hospital.getName();
            }
        }

        return closestHospital;
    }
}
