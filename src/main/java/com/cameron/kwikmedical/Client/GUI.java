package com.cameron.kwikmedical.Client;
import com.cameron.kwikmedical.Business.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Class for constructing GUI
 */
public class GUI {
    public GUI() {
        JFrame frame = new JFrame();
        frame.setResizable(false);
        JPanel panel = new JPanel();
        JLabel titleLabel = new JLabel("KwikMedical");
        JLabel bodyLabel = new JLabel("Please enter...");
        JButton submitButton = new JButton("Submit");
        panel.setBorder(BorderFactory.createEmptyBorder(500, 500, 500, 500));
        panel.setLayout(new GridLayout(0, 1));

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("KwikMedical");
        frame.pack();
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        // CLI for now, will migrate to Swing GUI at a later date.
        try {
            /*Hospital hospital = new Hospital("Royal Hospital", "Napier", "Eh140ht");
            Hospital hospital2 = new Hospital("Glasgow", "Napier", "Eh240ht");
            Hospital hospital4 = new Hospital("Royal Hospital Electric Boogaloo", "Napier", "Eh130ft");
            Hospital hospital3 = new Hospital("Edinburgh", "Napier", "Eh50ht");
            Hospital hospital5 = new Hospital("London", "Napier", "Eh80ht");
            ArrayList<Hospital> hospitalList = new ArrayList<>();
            hospitalList.add(hospital);
            hospitalList.add(hospital2);
            hospitalList.add(hospital3);
            hospitalList.add(hospital4);
            hospitalList.add(hospital5);
            String test = hospital.LocateNearestHospital("Eh40ft", hospitalList);
            System.out.println(test);*/
            new GUI();
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter your first and last name: ");
            String nameInput = input.readLine();
            String[] namesSplit = nameInput.split(" ");
            System.out.println("Enter your NHS Number: ");
            String nhsInput = input.readLine();
            Integer nhsNumber = Integer.parseInt(nhsInput);
            System.out.println("Enter the address of the Emergency: ");
            String address = input.readLine();
            System.out.println("Enter the post code: ");
            String postCode = input.readLine();
            System.out.println("What is your medical condition: ");
            String condition = input.readLine();
            PatientDetails newCall = new PatientDetails(namesSplit[0], namesSplit[1], nhsNumber, address, postCode, condition);
            newCall.setPatientDetails(newCall);

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
