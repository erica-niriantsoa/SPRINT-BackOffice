package com.backoffice.config;

import java.net.URI;

public class DatabaseUrlParser {
    
    public static String getJdbcUrl() {
        String databaseUrl = System.getenv("DATABASE_URL");
        
        // Si pas de DATABASE_URL, utiliser la config locale
        if (databaseUrl == null || databaseUrl.isEmpty()) {
            return "jdbc:postgresql://localhost:5432/test_framework";
        }
        
        try {
            // Parser l'URL PostgreSQL de Render
            // Format: postgresql://user:password@host/database
            URI uri = new URI(databaseUrl);
            
            String host = uri.getHost();
            int port = uri.getPort() != -1 ? uri.getPort() : 5432;
            String database = uri.getPath().substring(1); // Enlever le / initial
            String userInfo = uri.getUserInfo();
            
            String username = "";
            String password = "";
            
            if (userInfo != null && userInfo.contains(":")) {
                String[] parts = userInfo.split(":", 2);
                username = parts[0];
                password = parts[1];
            }
            
            // Construire l'URL JDBC avec hostname complet
            String jdbcUrl = String.format(
                "jdbc:postgresql://%s.render.com:%d/%s?user=%s&password=%s&sslmode=require",
                host, port, database, username, password
            );
            
            return jdbcUrl;
                    
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse DATABASE_URL: " + e.getMessage(), e);
        }
    }
}