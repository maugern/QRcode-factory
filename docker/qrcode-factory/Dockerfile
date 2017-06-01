# QRcode-factory's Dockerfile ==================================================
# Release under MIT Licence
# For more information, https://github.com/maugern/QRcode-factory

# DEFINE IMAGE =================================================================
FROM maven:3-jdk-8
MAINTAINER Nicolas Mauger <https://github.com/maugern/>

# BEFORE INSTALL ===============================================================
ENV LANG en_US.UTF-8

# CONFIGURE MAVEN ==============================================================
ADD pom.xml /srv/QRcode-factory/
WORKDIR /srv/QRcode-factory/
RUN mvn install

# WEB SERVICE CONFIGURATION ====================================================
ADD src /srv/QRcode-factory/src/
EXPOSE 8080

# START THE WEB SERVER =========================================================
CMD mvn jetty:run -DskipTests=true
