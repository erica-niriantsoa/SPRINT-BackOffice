# Build stage
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copier le pom.xml et télécharger les dépendances
COPY pom.xml .
COPY lib/ lib/

# Copier le code source
COPY src/ src/

# Build l'application
RUN mvn clean package -DskipTests

# Runtime stage avec Tomcat
FROM tomcat:10.1-jre17

# Supprimer les applications par défaut de Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copier le WAR buildé
COPY --from=build /app/target/SPRINT-BackOffice.war /usr/local/tomcat/webapps/ROOT.war

# Exposer le port
EXPOSE 8080

# Démarrer Tomcat
CMD ["catalina.sh", "run"]