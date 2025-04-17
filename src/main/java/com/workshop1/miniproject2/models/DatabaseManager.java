package com.workshop1.miniproject2.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

        private static final String URL = "jdbc:mysql://localhost:3306/productdb";
        private static final String USER = "root";
        private static final String PASSWORD = "password";

        public static Connection getConnection() throws SQLException {
            try {
                return DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                throw new SQLException("Unable to connect to the database.", e);
            }
        }
    }

