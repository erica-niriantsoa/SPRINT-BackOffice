# Build stage
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copier le pom.xml et les libs
COPY pom.xml .
COPY lib/ lib/

# Installer le framework local dans Maven
RUN mvn install:install-file \
    -Dfile=lib/mh-framework.jar \
    -DgroupId=com.mhframework \
    -DartifactId=mhframework \
    -Dversion=1.0.0 \
    -Dpackaging=jar

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