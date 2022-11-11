package com.cameron.kwikmedical.Client;

import javax.swing.*;
import java.awt.*;

public class KwikMedical extends JFrame {
    public KwikMedical() {
        setContentPane(kwikMedicalPanel);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width/2, screenSize.height/2);
        setTitle("KwikMedical");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    private JButton hospitalButton;
    private JButton Operator;
    private JPanel kwikMedicalPanel;
}
