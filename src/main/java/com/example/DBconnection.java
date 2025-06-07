package com.example;

import java.sql.*;

public class DBconnection {

    private static final String URL = "jdbc:mysql://localhost:3306/librarymanager";
    private static final String user = "root";
    private static final String password = "Riy@2806";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,user,password);
    }

}
