package com.backoffice.config;

import java.net.URI;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DatabaseUrlParser {
    
    private static final String PROPERTIES_FILE = "src/main/webapp/WEB-INF/conf/mh.properties";

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(PROPERTIES_FILE)) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file: " + PROPERTIES_FILE, e);
        }
        return properties;
    }
    
    public static String getJdbcUrl() {
        Properties properties = loadProperties();
        return properties.getProperty("db.url");
    }
}