package com.cameron.kwikmedical.Business;

import java.time.LocalTime;

/**
 * Class for storing Call out details
 */
public class CallOutDetails {
    public CallOutDetails(String NHSNumber, String patientName, LocalTime timeOfIncident, String locationOfIncident, Integer timeSpentOnCall, String actionTaken, String incidentReport, String hospitalName) {
        this.NHSNumber = NHSNumber;
        this.PatientName = patientName;
        this.TimeOfIncident = timeOfIncident;
        this.LocationOfIncident = locationOfIncident;
        this.TimeSpentOnCall = timeSpentOnCall;
        this.ActionTaken = actionTaken;
        this.IncidentReport = incidentReport;
        this.HospitalName = hospitalName;
    }

    private String NHSNumber;
    private String PatientName;
    private LocalTime TimeOfIncident;
    private String LocationOfIncident;
    private Integer TimeSpentOnCall;
    private String ActionTaken;
    private String IncidentReport;
    private String HospitalName;

    public String getNHSNumber() {
        return NHSNumber;
    }

    public String getPatientName() {
        return PatientName;
    }

    public LocalTime getTimeOfIncident() {
        return TimeOfIncident;
    }

    public String getLocationOfIncident() {
        return LocationOfIncident;
    }

    public Integer getTimeSpentOnCall() {
        return TimeSpentOnCall;
    }

    public String getActionTaken() {
        return ActionTaken;
    }

    public String getIncidentReport() {
        return IncidentReport;
    }

    public String getHospitalName() {
        return HospitalName;
    }
}
