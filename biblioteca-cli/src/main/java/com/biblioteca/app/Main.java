package com.biblioteca.app;

import com.biblioteca.config.ConnectionFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("PRAGMA foreign_keys;")) {

            int foreignKeysFlag = resultSet.next() ? resultSet.getInt(1) : 0;
            System.out.println("Database connection OK");
            System.out.println("PRAGMA foreign_keys = " + foreignKeysFlag);
        } catch (Exception e) {
            System.err.println("Failed to initialize database connection: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
