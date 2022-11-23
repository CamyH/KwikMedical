package com.cameron.kwikmedical.Business;

import java.util.ArrayList;

/**
 * Class for setting the patient details when they use the system
 */
public class PatientDetails {
    public PatientDetails(String pFullName, String nhsNumber, String address, String medicalCond) {
        this.pFullName = pFullName;
        this.nhsNumber = nhsNumber;
        this.address = address;
        this.medicalCond = medicalCond;
    }

    private final ArrayList<PatientDetails> patientDetails = new ArrayList<>();

    private String pFullName;
    private String nhsNumber;
    private String address;
    private String medicalCond;

    public ArrayList<PatientDetails> getPatientDetails() {
        return patientDetails;
    }

    public void setpFullName(PatientDetails details) {
        this.patientDetails.add(details);
    }

    public String getpFullName() {
        return pFullName;
    }

    public void set(String pFirstName) {
        this.pFullName = pFirstName;
    }

    public String getNhsNumber() {
        return nhsNumber;
    }

    public void setNhsNumber(String nhsNumber) {
        this.nhsNumber = nhsNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMedicalCond() {
        return medicalCond;
    }

    public void setMedicalCond(String medicalCond) {
        this.medicalCond = medicalCond;
    }
}
