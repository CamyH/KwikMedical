package com.cameron.kwikmedical.Business;

import java.util.ArrayList;

/**
 * Class for setting the patient details when they use the system
 */
public class PatientDetails {
    public PatientDetails(String pFirstName, String pLastName, Integer nhsNumber, String address, String postCode, String medicalCond) {
        this.pFirstName = pFirstName;
        this.pLastName = pLastName;
        this.nhsNumber = nhsNumber;
        this.address = address;
        this.postCode = postCode;
        this.medicalCond = medicalCond;
    }

    private final ArrayList<PatientDetails> patientDetails = new ArrayList<>();

    private String pFirstName;
    private String pLastName;
    private Integer nhsNumber;
    private String address;
    private String postCode;
    private String medicalCond;

    public ArrayList<PatientDetails> getPatientDetails() {
        return patientDetails;
    }

    public void setPatientDetails(PatientDetails details) {
        this.patientDetails.add(details);
    }

    public String getpFirstName() {
        return pFirstName;
    }

    public void setpFirstName(String pFirstName) {
        this.pFirstName = pFirstName;
    }

    public String getpLastName() {
        return pLastName;
    }

    public void setpLastName(String pLastName) {
        this.pLastName = pLastName;
    }

    public Integer getNhsNumber() {
        return nhsNumber;
    }

    public void setNhsNumber(Integer nhsNumber) {
        this.nhsNumber = nhsNumber;
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

    public String getMedicalCond() {
        return medicalCond;
    }

    public void setMedicalCond(String medicalCond) {
        this.medicalCond = medicalCond;
    }
}
