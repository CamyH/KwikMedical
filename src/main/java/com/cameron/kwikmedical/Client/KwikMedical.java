package com.cameron.kwikmedical.Client;

import javax.swing.*;
import java.awt.*;
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
        PatientSearch.addActionListener(e -> {
            if (NHSNumberOperator.getText().equals("") || pName.getText().equals("") || AddressBox.getText().equals("") || MedicalCondBox.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter the NHS Number and Patient Name.");
            } else if(new Database().DBCheckIfPatientExists(NHSNumberOperator.getText())) {
                    JOptionPane.showMessageDialog(null, "Patient " + pName.getText() + " Found");
                    Hospital requestedHospital = GenerateAmbulanceRequest();
                    PatientDetails patient = new PatientDetails(pName.getText(), NHSNumberOperator.getText(), AddressBox.getText(), MedicalCondBox.getText());
                    new Database().DBSendDetailsToHospital(requestedHospital, patient, true);
                    // Clear input boxes
                    NHSNumberOperator.setText("");
                    pName.setText("");
                    AddressBox.setText("");
                    MedicalCondBox.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Patient does not exist, new patient added to database.");
                    // Adding new patient to Database before generating ambulance request and sending to hospital
                    PatientDetails newPatient = new PatientDetails(pName.getText(), NHSNumberOperator.getText(), AddressBox.getText(), MedicalCondBox.getText());
                    new Database().DBInsertPatientDetails(newPatient);
                    Hospital requestedHospital = GenerateAmbulanceRequest();
                    new Database().DBSendDetailsToHospital(requestedHospital, newPatient, true);
                    // Clear input boxes
                    NHSNumberOperator.setText("");
                    pName.setText("");
                    AddressBox.setText("");
                    MedicalCondBox.setText("");
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

            // Insert details into DB
            new Database().DBInsertCallOutDetails(nhsNumberBox.getText(), fullNameBox.getText(), timeOfIncident, incidentLocation.getText(), timeSpentBox.getText(), actionTakenBox.getText(), incidentReportBox.getText());
            JOptionPane.showMessageDialog(null, "Call Out Details Updated");
            // Clear input boxes
            nhsNumberBox.setText("");
            fullNameBox.setText("");
            TimeOfIncidentBox.setText("");
            incidentLocation.setText("");
            timeSpentBox.setText("");
            actionTakenBox.setText("");
            incidentReportBox.setText("");
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
    private JButton PatientSearch;
    private JComboBox HospitalList;
    private JTextField nhsNumberBox;
    private JTextField fullNameBox;
    private JTextArea incidentReportBox;
    private JTextField TimeOfIncidentBox;
    private JTextField incidentLocation;
    private JTextArea actionTakenBox;
    private JTextField timeSpentBox;
    private JButton SubmitButton;
    private JTextField AddressBox;
    private JTextField MedicalCondBox;
    private JPanel Hospital;
    private JComboBox HospitalSystemList;
    private JList RequestsBox;
    private JButton UpdateListButton;
}
