package com.cameron.kwikmedical.Client;

import javax.swing.*;
import java.awt.*;

public class KwikMedical extends JFrame {
    public KwikMedical() {
        setContentPane(KwikMedical);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width/2, screenSize.height/2);
        setTitle("KwikMedical");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }
    private JPanel KwikMedicalPanel;
    private JPanel KwikMedical;
    private JTabbedPane KwikMedicalTabs;
    private JPanel Hospital;
    private JPanel Operator;
    private JLabel CallDetails;
    private JTextField textField1;
    private JTextField NHSNumberOperator;
    private JTextField pName;
    private JTextField address;
    private JTextField medicalCond;
    private JButton PatientSearch;
    private JTextField textField2;
}
