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
            preparedStatement.setString(1, nhsNumber);

            // Execute query and store in result set
            ResultSet results = preparedStatement.executeQuery();

            String nhsNumberOutput = "";
            if(results.next())
                 nhsNumberOutput = results.getString(2);
            System.out.println(nhsNumberOutput);
            // Closing DB connection and statement
            TerminateDB();
            preparedStatement.close();
            return nhsNumberOutput.equals(nhsNumber);
        } catch (Exception err) {
            System.out.println("Error checking patient exists: " + err.getMessage());
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

    public PatientDetails DBRetrievePatientDetails(String nhsNumber) {
        try {
            // Connecting to DB
            DBConnection();
            // Setting up PreparedStatement to retrieve patient details from DB
            String query = "SELECT * FROM PatientDetails WHERE NHSNumber LIKE ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, nhsNumber);

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

    // Only used if wanting to add a new hospital to database
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
                        results.getString("NHSNumber"), results.getString("PatientAddress")
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

    public ArrayList<CallOutDetails> DBRetrieveCallOutDetails(String hospitalName) {
        try {
            // Connecting to DB
            DBConnection();
            // Setting up PreparedStatement to get call out details for specific NHS Number from DB
            String query = "SELECT * FROM CallOutDetails WHERE HospitalName LIKE ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, hospitalName);
            // Execute selection query
            ResultSet results = preparedStatement.executeQuery();
            ArrayList<CallOutDetails> allCallOuts = new ArrayList<>();
            while (results.next()) {
                CallOutDetails callout = new CallOutDetails(results.getString("NHSNumber"), results.getString("PatientName"), results.getTime("TimeOfIncident").toLocalTime()
                        , results.getString("LocationOfIncident"), results.getInt("TimeSpentOnCall"), results.getString("ActionTaken"), results.getString("IncidentReport"),
                        results.getString("HospitalName"));
                allCallOuts.add(callout);
            }

            //String hospitalNameDB = DBReturnHospitalName(NHSNumber);

            // Closing DB connection and statement
            TerminateDB();
            preparedStatement.close();
            return allCallOuts;
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
        return null;
    }

    public String DBReturnHospitalName(String NHSNumber) {
        try {
            // Connecting to DB
            DBConnection();
            String hospitalName = "";
            // Setting up PreparedStatement to grab request for the NHSNumber
            // This will allow for the HospitalName to be found to filter the call-out details
            String query = "SELECT * FROM HospitalSystem WHERE NHSNumber LIKE ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, NHSNumber);
            // Execute selection query
            ResultSet requestResults = preparedStatement.executeQuery();
            while (requestResults.next()) {
                hospitalName = requestResults.getString("HospitalName");
            }
            return hospitalName;
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

    public void DBInsertCallOutDetails(String nhsNumber, String fullName, LocalTime timeOfIncident, String locationOfIncident, String timeSpentOnCall, String actionTaken, String incidentReport, String hospitalName) {
        try {
            // Connecting to DB
            DBConnection();
            // Setting up PreparedStatement to insert patient details into DB
            String query = "INSERT INTO CallOutDetails (NHSNumber, PatientName, TimeOfIncident, LocationOfIncident, TimeSpentOnCall, ActionTaken, IncidentReport, HospitalName) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, nhsNumber);
            preparedStatement.setString(2, fullName);
            preparedStatement.setTime(3, Time.valueOf(timeOfIncident));
            preparedStatement.setString(4, locationOfIncident);
            preparedStatement.setInt(5, Integer.parseInt(timeSpentOnCall));
            preparedStatement.setString(6, actionTaken);
            preparedStatement.setString(7, incidentReport);
            preparedStatement.setString(8, hospitalName);

            // PreparedStatement to update patient's medical condition
            String patientDetailsQuery = "UPDATE PatientDetails SET MedicalCond = ? WHERE NHSNumber = ?";
            PreparedStatement preparedStatementPatient = conn.prepareStatement(patientDetailsQuery);
            preparedStatementPatient.setString(1, actionTaken);
            preparedStatementPatient.setString(2, nhsNumber);

            // PreparedStatement to remove pending request from hospital system
            String removeRequestQuery = "DELETE FROM HospitalSystem WHERE NHSNumber = ?";
            PreparedStatement prepareStatementRequest = conn.prepareStatement(removeRequestQuery);
            prepareStatementRequest.setString(1, nhsNumber);

            // Execute insertion query
            preparedStatement.execute();
            // Execute update query
            preparedStatementPatient.executeUpdate();
            // Execute delete query
            prepareStatementRequest.executeUpdate();

            // Closing DB connection and statement
            TerminateDB();
            preparedStatement.close();
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
    }
}