package com.cameron.kwikmedical.Client;
import com.cameron.kwikmedical.Business.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GUI {
    public static void main(String[] args) {
        // CLI for now, will migrate to Swing GUI at a later date.
        try {
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
