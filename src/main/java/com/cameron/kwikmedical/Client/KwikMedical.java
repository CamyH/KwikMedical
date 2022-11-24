package com.cameron.kwikmedical.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

import com.cameron.kwikmedical.Business.HospitalRequest;
import com.cameron.kwikmedical.Business.PatientDetails;
import com.cameron.kwikmedical.Database.Database;
import com.cameron.kwikmedical.Business.Hospital;

public class KwikMedical extends JFrame {
    public KwikMedical() {
        setContentPane(KwikMedical);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width/2, screenSize.height/2);
        setTitle("KwikMedical");
        setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        /**
         * Operator Panel
         * Searching for Patient
         * If Patient Exists, Generate ambulance request and send details to that Hospital
         * If Patient does not exist, add new patient to database, then generate ambulance request and send details to that Hospital
         */
        CreateRequestButton.addActionListener(e -> {
            if (NHSNumberOperator.getText().equals("") || pName.getText().equals("") || AddressBox.getText().equals("") || MedicalCondBox.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please fill out all the boxes to create a callout request.");
            } else if(new Database().DBCheckIfPatientExists(NHSNumberOperator.getText())) {
                    Hospital requestedHospital = GenerateAmbulanceRequest();
                    PatientDetails patient = new PatientDetails(pName.getText(), NHSNumberOperator.getText(), AddressBox.getText(), MedicalCondBox.getText());
                    new Database().DBSendDetailsToHospital(requestedHospital, patient, true);
                    JOptionPane.showMessageDialog(null, "Callout Created, sent to Hospital: " + requestedHospital);
                    // Clear input boxes
                    NHSNumberOperator.setText("");
                    pName.setText("");
                    AddressBox.setText("");
                    MedicalCondBox.setText("");
                } else {
                    // Adding new patient to Database before generating ambulance request and sending to hospital
                    PatientDetails newPatient = new PatientDetails(pName.getText(), NHSNumberOperator.getText(), AddressBox.getText(), MedicalCondBox.getText());
                    new Database().DBInsertPatientDetails(newPatient);
                    Hospital requestedHospital = GenerateAmbulanceRequest();
                    new Database().DBSendDetailsToHospital(requestedHospital, newPatient, true);
                    JOptionPane.showMessageDialog(null, "Patient does not exist, new patient added to database. Callout Created, sent to Hospital: " + requestedHospital);
                    // Clear input boxes
                    NHSNumberOperator.setText("");
                    pName.setText("");
                    AddressBox.setText("");
                    MedicalCondBox.setText("");
                }
        });

        SearchForPatientButton.addActionListener(e -> {
            if (NHSNumberOperator.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter a valid NHS Number to check if a patient exists.");
            } else if (new Database().DBCheckIfPatientExists(NHSNumberOperator.getText())) {
                JOptionPane.showMessageDialog(null, "Patient found.");
            }
        });

        SubmitButton.addActionListener(e -> {
            // Initialise time of incident as midnight by default
            LocalTime timeOfIncident = LocalTime.MIDNIGHT;
            // Basic input validation for time of incident
            if (TimeOfIncidentBox.getText().contains(":"))
                timeOfIncident = LocalTime.parse(TimeOfIncidentBox.getText());
            if (TimeOfIncidentBox.getText().contains(":"))
                timeOfIncident = LocalTime.parse(TimeOfIncidentBox.getText());
            else
                JOptionPane.showMessageDialog(null, "Please enter a valid time. (HH:MM)");

            // Check the patient exists before adding a new row to the callout details table
            if (new Database().DBCheckIfPatientExists(NHSNumberBox.getText())) {
                // Insert details into DB
                new Database().DBInsertCallOutDetails(NHSNumberBox.getText(), FullNameBox.getText(), timeOfIncident, IncidentLocation.getText(), TimeSpentBox.getText(), ActionTakenBox.getText(), IncidentReportBox.getText());
                JOptionPane.showMessageDialog(null, "Call Out Details Updated and Request Removed");
            } else {
                JOptionPane.showMessageDialog(null, "NHS Number does not exist. Try again.");
            }
            // Clear input boxes
            NHSNumberBox.setText("");
            FullNameBox.setText("");
            TimeOfIncidentBox.setText("");
            IncidentLocation.setText("");
            TimeSpentBox.setText("");
            ActionTakenBox.setText("");
            IncidentReportBox.setText("");
        });

        // If the Hospital Tab is selected, the hospital drop down is populated from the DB
        KwikMedicalTabs.addChangeListener(e -> PopulateHospitalLists());

        UpdateListButton.addActionListener(e -> {
            PopulateRequestsBox();
            JOptionPane.showMessageDialog(null, "Requests Updated");
        });
    }
    private void PopulateHospitalLists() {
        ArrayList<Hospital> allHospitals = new Database().DBRetrieveAllHospitals();
        for (Hospital hospital : allHospitals) {
            //HospitalList.addItem(hospital.getName());
            HospitalSystemList.addItem(hospital.getName());
        }
    }

    private void PopulateRequestsBox() {
        String hospitalName = HospitalSystemList.getSelectedItem().toString();
        DefaultListModel requestModel = new DefaultListModel<>();
        ArrayList<HospitalRequest> allRequests = new Database().DBRetrieveHospitalRequests(hospitalName);
        requestModel.clear();
        for (HospitalRequest request : allRequests) {
            requestModel.addElement("Hospital Name: " + request.getHospitalName());
            requestModel.addElement("Hospital Address: " + request.getHospitalAddress());
            requestModel.addElement("Patient Name: " + request.getPatientName());
            requestModel.addElement("Patient NHS Number: " + request.getnHSNumber());
            requestModel.addElement("Medical Condition: " + request.getMedicalCond());
            requestModel.addElement("Ambulance Sent: " + request.getAmbulanceSent());
        }
        RequestsBox.setModel(requestModel);
    }

    private Hospital GenerateAmbulanceRequest() {
        Random randInt = new Random();
        ArrayList<Hospital> allHospitals = new Database().DBRetrieveAllHospitals();
        return allHospitals.get(randInt.nextInt(0, 20));
    }

    private JPanel KwikMedicalPanel;
    private JPanel KwikMedical;
    private JTabbedPane KwikMedicalTabs;
    private JPanel Device;
    private JPanel Operator;
    private JLabel CallDetails;
    private JTextField NHSNumberOperator;
    private JTextField pName;
    private JButton CreateRequestButton;
    private JComboBox HospitalList;
    private JTextField NHSNumberBox;
    private JTextField FullNameBox;
    private JTextArea IncidentReportBox;
    private JTextField TimeOfIncidentBox;
    private JTextField IncidentLocation;
    private JTextArea ActionTakenBox;
    private JTextField TimeSpentBox;
    private JButton SubmitButton;
    private JTextField AddressBox;
    private JTextField MedicalCondBox;
    private JPanel Hospital;
    private JComboBox HospitalSystemList;
    private JList RequestsBox;
    private JButton UpdateListButton;
    private JButton SearchForPatientButton;
}
