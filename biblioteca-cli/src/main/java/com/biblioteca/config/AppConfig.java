package com.biblioteca.config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public final class AppConfig {
    private static final String DEFAULT_PROPERTIES_PATH = "src/main/resources/application.properties";
    private static final Properties PROPERTIES = loadProperties();

    private AppConfig() {
    }

    public static String getRequired(String key) {
        String value = PROPERTIES.getProperty(key);
        if (value == null || value.isBlank()) {
            throw new IllegalStateException("Missing required property: " + key);
        }
        return value.trim();
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        String value = PROPERTIES.getProperty(key);
        if (value == null || value.isBlank()) {
            return defaultValue;
        }
        return Boolean.parseBoolean(value.trim());
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();

        try (InputStream classpathStream = AppConfig.class.getClassLoader()
            .getResourceAsStream("application.properties")) {
            if (classpathStream != null) {
                properties.load(classpathStream);
                return properties;
            }
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load application.properties from classpath", e);
        }

        Path fallbackPath = Paths.get(DEFAULT_PROPERTIES_PATH);
        if (!Files.exists(fallbackPath)) {
            throw new IllegalStateException(
                "application.properties not found in classpath or at " + fallbackPath.toAbsolutePath()
            );
        }

        try (InputStream fileStream = Files.newInputStream(fallbackPath)) {
            properties.load(fileStream);
            return properties;
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load properties from " + fallbackPath.toAbsolutePath(), e);
        }
    }
}
