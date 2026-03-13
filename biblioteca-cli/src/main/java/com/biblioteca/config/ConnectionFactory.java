package com.biblioteca.config;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public final class ConnectionFactory {
    private static final String DB_FILE_PATH = AppConfig.getRequired("db.file.path");
    private static final boolean FOREIGN_KEYS_ENABLED = AppConfig.getBoolean("db.foreign_keys", true);

    private ConnectionFactory() {
    }

    public static Connection getConnection() throws SQLException {
        Path databasePath = resolveDatabasePath(DB_FILE_PATH);
        String jdbcUrl = "jdbc:sqlite:" + databasePath;

        Connection connection = DriverManager.getConnection(jdbcUrl);
        if (FOREIGN_KEYS_ENABLED) {
            try (Statement statement = connection.createStatement()) {
                statement.execute("PRAGMA foreign_keys = ON;");
            }
        }
        return connection;
    }

    private static Path resolveDatabasePath(String configuredPath) {
        try {
            Path databasePath = Paths.get(configuredPath).toAbsolutePath().normalize();
            Path parent = databasePath.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }
            return databasePath;
        } catch (Exception e) {
            throw new IllegalStateException("Invalid database file path: " + configuredPath, e);
        }
    }
}