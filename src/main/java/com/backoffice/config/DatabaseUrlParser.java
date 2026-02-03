package config;

import java.net.URI;

public class DatabaseUrlParser {
    
    public static String getJdbcUrl() {
        String databaseUrl = System.getenv("DATABASE_URL");
        
        // Si pas de DATABASE_URL, utiliser la config locale
        if (databaseUrl == null || databaseUrl.isEmpty()) {
            return "jdbc:postgresql://localhost:5432/test_framework";
        }
        
        try {
            // Remplacer postgresql:// par jdbc:postgresql://
            if (databaseUrl.startsWith("postgresql://")) {
                databaseUrl = "jdbc:" + databaseUrl;
            }
            
            // Ajouter sslmode=require pour Render
            if (!databaseUrl.contains("sslmode=")) {
                databaseUrl += "?sslmode=require";
            }
            
            return databaseUrl;
                    
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse DATABASE_URL: " + e.getMessage(), e);
        }
    }
}