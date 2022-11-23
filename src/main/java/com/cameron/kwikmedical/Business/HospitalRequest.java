package com.cameron.kwikmedical.Business;

/**
 * Class for storing Request details
 */
public class HospitalRequest {
    public HospitalRequest(String hospitalName, String hospitalAddress, String patientName, String nHSNumber, String patientAddress, String medicalCond, Boolean ambulanceSent) {
        this.hospitalName = hospitalName;
        this.hospitalAddress = hospitalAddress;
        this.patientName = patientName;
        this.nHSNumber = nHSNumber;
        this.patientAddress = patientAddress;
        this.medicalCond = medicalCond;
        this.ambulanceSent = ambulanceSent;
    }

    private String hospitalName;
    private String hospitalAddress;
    private String patientName;
    private String nHSNumber;
    private String patientAddress;
    private String medicalCond;
    private Boolean ambulanceSent;

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getHospitalAddress() {
        return hospitalAddress;
    }

    public void setHospitalAddress(String hospitalAddress) {
        this.hospitalAddress = hospitalAddress;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getnHSNumber() {
        return nHSNumber;
    }

    public void setnHSNumber(String nHSNumber) {
        this.nHSNumber = nHSNumber;
    }

    public String getPatientAddress() {
        return patientAddress;
    }

    public void setPatientAddress(String patientAddress) {
        this.patientAddress = patientAddress;
    }

    public String getMedicalCond() {
        return medicalCond;
    }

    public void setMedicalCond(String medicalCond) {
        this.medicalCond = medicalCond;
    }

    public Boolean getAmbulanceSent() {
        return ambulanceSent;
    }

    public void setAmbulanceSent(Boolean ambulanceSent) {
        this.ambulanceSent = ambulanceSent;
    }
}
