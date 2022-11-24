package com.cameron.kwikmedical;

import com.cameron.kwikmedical.Database.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class DatabaseTest {
    static Database database;

    /**
     * Prepare for the tests by initialising database class
     */
    @BeforeAll
    static void init() {
        database = new Database();
    }

    /**
     * Query the database to check if a patient exists
     * Confirm that the method returns the correct answer
     */
    @Test
    void DBCheckIfPatientExists() {
        Boolean correctAnswer = true;
        // NHS Number for patient "James Bond" used for testing
        String testNhsNum = "1234567899";
        Assertions.assertEquals(correctAnswer, database.DBCheckIfPatientExists(testNhsNum));
    }

    /**
     * Query the database and return all the hospitals
     * Confirm that the method returns an array list of the correct length
     * Size of the array should be 20 as that is the number of specified hospitals
     */
    @Test
    void DBRetrieveAllHospitals() {
        Assertions.assertEquals(20, database.DBRetrieveAllHospitals().size());
    }

    /**
     * Query the database and return the details of the specified patient
     * Confirm that the method returns an object containing the correct patient details
     * Checking the NHS Number as that is unique
     */
    @Test
    void DBRetrievePatientDetails() {
        // NHS Number for patient "James Bond" used for testing
        String testNHSNumber = "1234567899";
        Assertions.assertEquals(testNHSNumber, database.DBRetrievePatientDetails(testNHSNumber).getNhsNumber());
    }

    /**
     * Query the database and return all hospital requests for the specified hospital
     * Confirm that the method returns an array list containing the correct hospital requests
     */
    @Test
    void DBRetrieveAllHospitalRequests() {
        // Hospital "Glasgow Road Hospital" used for testing
        String hospitalName = "Glasgow Road Hospital";
        // Expected size is 1 as there is only one request for the test hospital
        Assertions.assertEquals(1, database.DBRetrieveHospitalRequests(hospitalName).size());
    }
}
