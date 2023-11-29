package org.practice.morning_classes.day_4.rest_example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/userDB";
        String user = "root";
        String password = "DEannd87!mysql";
        return DriverManager.getConnection(url, user, password);
    }
}
