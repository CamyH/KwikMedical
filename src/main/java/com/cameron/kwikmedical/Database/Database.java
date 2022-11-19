package com.cameron.kwikmedical.Database;
import com.cameron.kwikmedical.Business.PatientDetails;

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


                results.next();
                Integer nhsNumberOutput = results.getInt(3);
                System.out.println(nhsNumberOutput);
                // Closing DB connection and statement
                TerminateDB();
                preparedStatement.close();
                return nhsNumberOutput.equals(Integer.parseInt(nhsNumber));
            } catch (Exception err) {
                System.out.println(err.getMessage());
            }
            return false;
        }

    public void DBInsertPatientDetails(String firstName, String lastName, Integer nhsNumber, String address, String postCode, String medicalCond) {
        try {
            // Connecting to DB
            DBConnection();
            // Setting up PreparedStatement to insert patient details into DB
            String query = "INSERT INTO PatientDetails (FirstName, LastName, NHSNumber, Address, PostCode, MedicalCond) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, nhsNumber);
            preparedStatement.setString(4, address);
            preparedStatement.setString(5, postCode);
            preparedStatement.setString(6, medicalCond);

            // Execute insertion query
            preparedStatement.execute();

            // Closing DB connection and statement
            TerminateDB();
            preparedStatement.close();
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
    }

    public ArrayList<PatientDetails> DBRetrievePatientDetails(Integer nhsNumber) {
        try {
            // Connecting to DB
            DBConnection();
            // Setting up PreparedStatement to insert patient details into DB
            String query = "SELECT * FROM PatientDetails WHERE NHSNumber LIKE ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, nhsNumber);

            // Execute insertion query
            ResultSet results = preparedStatement.executeQuery();
            ArrayList<PatientDetails> details = new ArrayList<>();
            while (results.next()) {
                PatientDetails patient = new PatientDetails(results.getString("FirstName"), results.getString("LastName"),
                        results.getInt("NHSNumber"), results.getString("Address"),
                        results.getString("PostCode"), results.getString("MedicalCond"));
                details.add(patient);
            }

            // Closing DB connection and statement
            TerminateDB();
            preparedStatement.close();

            return details;
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
        return null;
    }

    public void DBUpdateCallOutDetails(Integer nhsNumber, String calloutDetails) {
        try {
            // Connecting to DB
            DBConnection();
            // Setting up PreparedStatement to insert patient details into DB
            String query = "";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, calloutDetails);

            // Execute insertion query
            preparedStatement.executeQuery();

            // Closing DB connection and statement
            TerminateDB();
            preparedStatement.close();
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
    }

    public void DBAddHospital(String name, String address, String postCode) {
        try {
            // Connecting to DB
            DBConnection();
            // Setting up PreparedStatement to insert patient details into DB
            String query = "INSERT INTO Hospitals (Name, Address, PostCode) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, address);
            preparedStatement.setString(3, postCode);

            // Execute insertion query
            preparedStatement.execute();

            // Closing DB connection and statement
            TerminateDB();
            preparedStatement.close();
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
    }
}
