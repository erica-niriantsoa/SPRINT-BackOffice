# Base image avec Java 17
FROM eclipse-temurin:17-jdk

# Variables Tomcat
ENV CATALINA_HOME /usr/local/tomcat
ENV PATH $CATALINA_HOME/bin:$PATH
ENV TOMCAT_VERSION 10.1.28

# Installer Tomcat
RUN apt-get update && apt-get install -y wget unzip && \
    wget https://downloads.apache.org/tomcat/tomcat-10/v${TOMCAT_VERSION}/bin/apache-tomcat-${TOMCAT_VERSION}.tar.gz && \
    tar xzf apache-tomcat-${TOMCAT_VERSION}.tar.gz && \
    mv apache-tomcat-${TOMCAT_VERSION} $CATALINA_HOME && \
    rm apache-tomcat-${TOMCAT_VERSION}.tar.gz

# Copier le WAR dans Tomcat
COPY target/SPRINT-BackOffice.war $CATALINA_HOME/webapps/

# Exposer le port 8080
EXPOSE 8080

# Démarrer Tomcat
CMD ["catalina.sh", "run"]
