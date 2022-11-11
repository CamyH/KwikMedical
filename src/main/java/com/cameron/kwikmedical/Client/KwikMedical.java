package com.cameron.kwikmedical.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KwikMedical extends JFrame {
    public KwikMedical() {
        CardLayout layout = (CardLayout)KwikMedical.getLayout();
        setContentPane(KwikMedical);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width/2, screenSize.height/2);
        setTitle("KwikMedical");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        HospitalBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layout.show(KwikMedical, "OperatorPanel");
            }
        });
        OperatorBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    private JButton HospitalBtn;
    private JButton OperatorBtn;
    private JPanel KwikMedicalPanel;
    private JPanel OperatorPanel;
    private JPanel KwikMedical;
}
