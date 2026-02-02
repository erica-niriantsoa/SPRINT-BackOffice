package com.backoffice.controller;

import config.DatabaseUrlParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/test-db")
public class TestDbServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><title>Test Database</title></head>");
        out.println("<body>");
        out.println("<h1>Test de connexion PostgreSQL</h1>");
        
        try {
            // ✅ Utilise DatabaseUrlParser
            String databaseUrl = DatabaseUrlParser.getJdbcUrl();
            
            out.println("<p><strong>URL de connexion :</strong> " + databaseUrl.replaceAll(":[^:@]+@", ":****@") + "</p>");
            
            // Tester la connexion
            Connection conn = DriverManager.getConnection(databaseUrl);
            
            out.println("<p style='color: green; font-weight: bold;'>✅ Connexion réussie !</p>");
            
            // Tester une requête
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT version(), current_database(), current_user");
            
            if (rs.next()) {
                out.println("<h2>Informations de la base :</h2>");
                out.println("<ul>");
                out.println("<li><strong>Version PostgreSQL :</strong> " + rs.getString(1) + "</li>");
                out.println("<li><strong>Base de données :</strong> " + rs.getString(2) + "</li>");
                out.println("<li><strong>Utilisateur :</strong> " + rs.getString(3) + "</li>");
                out.println("</ul>");
            }
            
            rs.close();
            stmt.close();
            conn.close();
            
        } catch (Exception e) {
            out.println("<p style='color: red;'>❌ Erreur de connexion !</p>");
            out.println("<pre>" + e.getMessage() + "</pre>");
            e.printStackTrace(out);
        }
        
        out.println("<p><a href='/'>← Retour à l'accueil</a></p>");
        out.println("</body>");
        out.println("</html>");
    }
}