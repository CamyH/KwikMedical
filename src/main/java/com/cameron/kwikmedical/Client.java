package com.cameron.kwikmedical;

import java.io.*;
import java.net.Socket;

public class Client {
    private static Socket sock;
    public static void main(String[] args) {
        ServerSettings serverSettings = new ServerSettings();
        try
        {
            // Get port number of server
            int port = serverSettings.getPort();

            // First create the input from keyboard
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Client Program");

            // Next we need to find out the IP address and port number of the server
            System.out.print("Enter IP Address of server: ");
            String ip = input.readLine();

            sock = new Socket(ip, port);

            // Create the incoming stream to read messages from
            DataInputStream network = new DataInputStream(sock.getInputStream());

            DataOutputStream sendMessage = new DataOutputStream(sock.getOutputStream());


            // Display our address
            System.out.println("Address: " + sock.getInetAddress());
            String line;

            // Loop until the connection closes, reading from the network
            while ((line = network.readUTF()) != null)
            {
                // Display the received message
                System.out.println(line);
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
                PatientDetails newPatient = new PatientDetails(namesSplit[0], namesSplit[1], nhsNumber, address, postCode, condition);
                // Assuming that the first word is the first name, second word is last name
                //newPatient.setpFirstName(namesSplit[0]);
                //newPatient.setpLastName(namesSplit[1]);
                sendMessage.writeUTF(newPatient.toString());
            }
        }
        catch (IOException ioe)
        {
            // This is expected when the server closes the network connection
            System.err.println("Error in I/O");
            System.err.println(ioe.getMessage());
            System.exit(-1);
        }
    }
}