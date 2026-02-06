package com.backoffice;

import com.backoffice.config.DatabaseUrlParser;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestConnection {
    public static void main(String[] args) {
        System.out.println("=== Test de connexion Supabase ===\n");
        
        try {
            // Charger le driver PostgreSQL
            Class.forName("org.postgresql.Driver");
            System.out.println("✓ Driver PostgreSQL chargé");
            
            // Obtenir l'URL JDBC
            String jdbcUrl = DatabaseUrlParser.getJdbcUrl();
            String maskedUrl = jdbcUrl.replaceAll("password=[^&]+", "password=****");
            System.out.println("✓ URL JDBC: " + maskedUrl);
            
            // Tenter la connexion
            System.out.println("\nConnexion en cours...");
            Connection conn = DriverManager.getConnection(jdbcUrl);
            System.out.println("✅ CONNEXION RÉUSSIE !\n");
            
            // Récupérer les infos de la base
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                "SELECT version(), current_database(), current_user, inet_server_addr(), inet_server_port()"
            );
            
            if (rs.next()) {
                System.out.println("📊 Informations de la base:");
                System.out.println("  - Version PostgreSQL: " + rs.getString(1).split(" ")[0] + " " + rs.getString(1).split(" ")[1]);
                System.out.println("  - Base de données: " + rs.getString(2));
                System.out.println("  - Utilisateur connecté: " + rs.getString(3));
                System.out.println("  - Serveur: " + rs.getString(4) + ":" + rs.getString(5));
            }
            
            // Tester une requête simple
            System.out.println("\n📋 Test de requête:");
            ResultSet rs2 = stmt.executeQuery("SELECT NOW() as heure_serveur");
            if (rs2.next()) {
                System.out.println("  - Heure du serveur: " + rs2.getTimestamp(1));
            }
            
            rs.close();
            rs2.close();
            stmt.close();
            conn.close();
            
            System.out.println("\n✅ Tous les tests sont passés avec succès !");
            
        } catch (Exception e) {
            System.err.println("\n❌ ERREUR DE CONNEXION:");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
