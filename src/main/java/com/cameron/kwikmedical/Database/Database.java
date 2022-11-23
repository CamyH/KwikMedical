package com.cameron.kwikmedical.Database;
import com.cameron.kwikmedical.Business.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.sql.*;

/**
 * Class for Database operations
 */
public class Database {
    private Connection conn;
    public void DBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kwikmedical?user=Java&password=Java");
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
    }

    public void TerminateDB() throws SQLException {
        conn.close();
    }

    public Boolean DBCheckIfPatientExists(String nhsNumber) {
        try {
            // Connecting to DB
            DBConnection();
            // Setting up PreparedStatement to query DB
            String query = "SELECT * FROM PatientDetails WHERE NHSNumber LIKE ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            int nhsNum = Integer.parseInt(nhsNumber);
            preparedStatement.setInt(1, nhsNum);

            // Execute query and store in result set
            ResultSet results = preparedStatement.executeQuery();

            Integer nhsNumberOutput = 0;
            if(results.next())
                 nhsNumberOutput = results.getInt(2);
            System.out.println(nhsNumberOutput);
            // Closing DB connection and statement
            TerminateDB();
            preparedStatement.close();
            return nhsNumberOutput.equals(Integer.parseInt(nhsNumber));
        } catch (Exception err) {
            System.out.println("Error with Database: " + err.getMessage());
        }
        return false;
    }

    public void DBInsertPatientDetails(PatientDetails patient) {
        try {
            String fullName = patient.getpFullName();
            String nhsNumber = patient.getNhsNumber();
            String address = patient.getAddress();
            String medicalCond = patient.getMedicalCond();
            // Connecting to DB
            DBConnection();
            // Setting up PreparedStatement to insert patient details into DB
            String query = "INSERT INTO PatientDetails (FullName, NHSNumber, Address, MedicalCond) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, fullName);
            preparedStatement.setString(2, nhsNumber);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, medicalCond);

            // Execute insertion query
            preparedStatement.execute();

            // Closing DB connection and statement
            TerminateDB();
            preparedStatement.close();
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
    }

    public PatientDetails DBRetrievePatientDetails(Integer nhsNumber) {
        try {
            // Connecting to DB
            DBConnection();
            // Setting up PreparedStatement to retrieve patient details from DB
            String query = "SELECT * FROM PatientDetails WHERE NHSNumber LIKE ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, nhsNumber);

            // Execute insertion query
            ResultSet results = preparedStatement.executeQuery();
            PatientDetails patient = null;
            while (results.next()) {
                patient = new PatientDetails(results.getString("FullName"),
                        results.getString("NHSNumber"), results.getString("Address")
                        , results.getString("MedicalCond"));
            }

            // Closing DB connection and statement
            TerminateDB();
            preparedStatement.close();

            return patient;
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
        return null;
    }

    public void DBAddHospital(String name, String address) {
        try {
            // Connecting to DB
            DBConnection();
            // Setting up PreparedStatement to insert hospitals into DB
            String query = "INSERT INTO Hospitals (Name, Address) VALUES (?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, address);

            // Execute insertion query
            preparedStatement.execute();

            // Closing DB connection and statement
            TerminateDB();
            preparedStatement.close();
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
    }

    public void DBSendDetailsToHospital(Hospital hospital, PatientDetails patient, Boolean ambulanceSent) {
        try {
            // Connecting to DB
            DBConnection();
            // Setting up PreparedStatement to insert hospitals into DB
            String query = "INSERT INTO HospitalSystem (HospitalName, HospitalAddress, PatientName, NHSNumber, PatientAddress, MedicalCond, AmbulanceSent) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, hospital.getName());
            preparedStatement.setString(2, hospital.getAddress());
            preparedStatement.setString(3, patient.getpFullName());
            preparedStatement.setString(4, patient.getNhsNumber());
            preparedStatement.setString(5, patient.getAddress());
            preparedStatement.setString(6, patient.getMedicalCond());
            preparedStatement.setBoolean(7, ambulanceSent);

            // Execute insertion query
            preparedStatement.execute();

            // Closing DB connection and statement
            TerminateDB();
            preparedStatement.close();
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
    }

    public ArrayList<HospitalRequest> DBRetrieveHospitalRequests(String hospitalName) {
        try {
            // Connecting to DB
            DBConnection();
            // Setting up PreparedStatement to insert patient details into DB
            String query = "SELECT * FROM HospitalSystem WHERE HospitalName LIKE ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, hospitalName);
            // Execute selection query
            ResultSet results = preparedStatement.executeQuery();
            ArrayList<HospitalRequest> allRequests = new ArrayList<>();
            while (results.next()) {
                HospitalRequest request = new HospitalRequest(results.getString("HospitalName"), results.getString("HospitalAddress"), results.getString("PatientName"),
                        results.getInt("NHSNumber"), results.getString("PatientAddress")
                        , results.getString("MedicalCond"), results.getBoolean("AmbulanceSent"));
                allRequests.add(request);
            }

            // Closing DB connection and statement
            TerminateDB();
            preparedStatement.close();
            return allRequests;
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
        return null;
    }

    public ArrayList<Hospital> DBRetrieveAllHospitals() {
        try {
            // Connecting to DB
            DBConnection();
            // Setting up PreparedStatement to insert patient details into DB
            String query = "SELECT * FROM Hospitals";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            // Execute selection query
            ResultSet results = preparedStatement.executeQuery();
            ArrayList<Hospital> hospitalList = new ArrayList<>();
            while (results.next()) {
                Hospital newHospital = new Hospital(results.getString("Name"), results.getString("Address"));
                hospitalList.add(newHospital);
            }

            // Closing DB connection and statement
            TerminateDB();
            preparedStatement.close();
            return hospitalList;
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
        return null;
    }

    public void DBInsertCallOutDetails(String nhsNumber, String fullName, LocalTime timeOfIncident, String locationOfIncident, String timeSpentOnCall, String actionTaken, String incidentReport) {
        try {
            // Connecting to DB
            DBConnection();
            // Setting up PreparedStatement to insert patient details into DB
            String query = "INSERT INTO CallOutDetails (NHSNumber, PatientName, TimeOfIncident, LocationOfIncident, TimeSpentOnCall, ActionTaken, IncidentReport) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, Integer.parseInt(nhsNumber));
            preparedStatement.setString(2, fullName);
            preparedStatement.setTime(3, Time.valueOf(timeOfIncident));
            preparedStatement.setString(4, locationOfIncident);
            preparedStatement.setInt(5, Integer.parseInt(timeSpentOnCall));
            preparedStatement.setString(6, actionTaken);
            preparedStatement.setString(7, incidentReport);

            // PreparedStatement to update patient's medical condition
            String patientDetailsQuery = "UPDATE PatientDetails SET MedicalCond = ? WHERE NHSNumber = ?";
            PreparedStatement preparedStatementPatient = conn.prepareStatement(patientDetailsQuery);
            preparedStatementPatient.setString(1, actionTaken);
            preparedStatementPatient.setInt(2, Integer.parseInt(nhsNumber));

            // Execute insertion query
            preparedStatement.execute();
            // Execute update query
            preparedStatementPatient.executeUpdate();

            // Closing DB connection and statement
            TerminateDB();
            preparedStatement.close();
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
    }
}