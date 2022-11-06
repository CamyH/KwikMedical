package com.cameron.kwikmedical.Database;
import java.sql.*;

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

    public Boolean DBCheckIfPatientExists(Integer nhsNumber) {
        try {
            // Connecting to DB
            DBConnection();
            // Setting up PreparedStatement to query DB
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM PatientDetails WHERE NHSNumber LIKE ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, nhsNumber);

            // Execute query and store in result set
            ResultSet results = preparedStatement.executeQuery();

            // Closing DB connection
            TerminateDB();

            Integer nhsNumberOutput = results.getInt(3);
            return nhsNumberOutput.equals(nhsNumber);
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
            Statement statement = conn.createStatement();
            String query = "INSERT INTO PatientDetails (FirstName, LastName, NHSNumber, Address, PostCode, MedicalCond) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, nhsNumber);
            preparedStatement.setString(4, address);
            preparedStatement.setString(5, postCode);
            preparedStatement.setString(6, medicalCond);

            // Execute insertion query
            preparedStatement.executeQuery();

            // Closing DB connection
            TerminateDB();
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
    }

    public void DBRetrievePatientDetails(Integer nhsNumber) {

    }

    public void DBUpdateCallOutDetails() {

    }
}
