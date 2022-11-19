package com.cameron.kwikmedical;

import com.cameron.kwikmedical.Client.*;
import com.cameron.kwikmedical.Database.*;
import com.cameron.kwikmedical.Business.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class DatabaseTest {
    static Database database;
    private static Connection conn;
    @BeforeAll
    static void init() {
        database = new Database();
    }
}
