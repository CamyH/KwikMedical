package com.cameron.kwikmedical.Client;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.time.LocalTime;
import java.util.ArrayList;

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

        PatientSearch.addActionListener(e -> {
            if (NHSNumberOperator.getText().equals("") || pName.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter the NHS Number and Patient Name");
            } else {
                if(new Database().DBCheckIfPatientExists(NHSNumberOperator.getText()))
                    JOptionPane.showMessageDialog(null, "Patient " + pName.getText() + " Found");
                else
                    JOptionPane.showMessageDialog(null, "Patient does not exist");
            }
        });

        SubmitButton.addActionListener(e -> {
            // Initialise time as midnight
            LocalTime timeOfIncident = LocalTime.MIDNIGHT;
            //Integer nhsNum = Integer.parseInt(nhsNumberBox.getText());
            //String fullName = fullNameBox.getText();
            if (timeOfIncidentBox.getText().contains(":"))
                timeOfIncident = LocalTime.parse(timeOfIncidentBox.getText());
            else
                JOptionPane.showMessageDialog(null, "Please enter a valid time. (HH:MM)");
            //String location = incidentLocation.getText();
            //String timeSpent = timeSpentBox.getText();
            //String actionTaken = actionTakenBox.getText();
            //String incidentReport = incidentReportBox.getText();

            // Parse time of incident to seconds and store in integer for use in db
            int timeInSeconds = timeOfIncident.toSecondOfDay();
            System.out.println(timeOfIncident);
        });

        // If the Hospital Tab is selected, the hospital drop down is populated from the DB
        KwikMedicalTabs.addChangeListener(e -> PopulateHospitalList());
    }
    private void PopulateHospitalList() {
        ArrayList<Hospital> allHospitals = new Database().DBRetrieveAllHospitals();
        for (Hospital hospital : allHospitals) {
            HospitalList.addItem(hospital.getName());
        }
    }
    private JPanel KwikMedicalPanel;
    private JPanel KwikMedical;
    private JTabbedPane KwikMedicalTabs;
    private JPanel Hospital;
    private JPanel Operator;
    private JLabel CallDetails;
    private JTextField NHSNumberOperator;
    private JTextField pName;
    private JButton PatientSearch;
    private JComboBox HospitalList;
    private JTextField nhsNumberBox;
    private JTextField fullNameBox;
    private JTextArea incidentReportBox;
    private JTextField timeOfIncidentBox;
    private JTextField incidentLocation;
    private JTextArea actionTakenBox;
    private JTextField timeSpentBox;
    private JButton SubmitButton;
}
