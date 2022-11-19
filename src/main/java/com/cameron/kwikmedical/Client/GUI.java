package com.cameron.kwikmedical.Client;
import com.cameron.kwikmedical.Business.*;
import com.cameron.kwikmedical.Database.Database;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Class for calling GUI
 */
public class GUI {
    public GUI() {
        KwikMedical GUI = new KwikMedical();
    }
    public static void main(String[] args) {
        new GUI();
        Database db = new Database();
        ArrayList<Hospital> allHospitals = db.DBRetrieveAllHospitals();
        System.out.println(allHospitals.size());
    }
}
