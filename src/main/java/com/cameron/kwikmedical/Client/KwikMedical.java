package com.cameron.kwikmedical.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.cameron.kwikmedical.Database.Database;

public class KwikMedical extends JFrame {
    public KwikMedical() {
        setContentPane(KwikMedical);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width/2, screenSize.height/2);
        setTitle("KwikMedical");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        PatientSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Database db = new Database();
                if (NHSNumberOperator.getText().equals("") || pName.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter the NHS Number and Patient Name");
                } else {
                    if(db.DBCheckIfPatientExists(NHSNumberOperator.getText()))
                        JOptionPane.showMessageDialog(null, "Patient " + pName.getText() + " Found");
                    else
                        JOptionPane.showMessageDialog(null, "Patient does not exist");
                }
            }
        });
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
}
