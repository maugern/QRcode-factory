# QRcode-factory's Dockerfile ==================================================
# Release under MIT Licence
# For more information, github.com/maugern/QRcode-factory

# DEFINE IMAGE =================================================================
FROM debian:jessie
MAINTAINER Nicolas Mauger <https://github.com/maugern/>

# BEFORE INSTALL ===============================================================
# Sets language to UTF8 : this works in pretty much all cases
ENV LANG en_US.UTF-8

# INSTALL JAVA 7 & MAVEN & SQLite ==============================================
RUN apt-get update && \
    apt-get install --fix-missing -y \
            openjdk-7-jdk \
            maven \
            sqlite3

# CONFIGURE JAVA ===============================================================
ENV JAVA_HOME /usr/lib/jvm/java-1.7.0-openjdk-amd64
ENV PATH $PATH:$JAVA_HOME/bin
ENV CLASSPATH $JAVA_HOME/lib/tools.jar

# CONFIGURE MAVEN ==============================================================
ADD pom.xml /srv/QRcode-factory/
WORKDIR /srv/QRcode-factory/
RUN mvn install

# SQLITE & database ============================================================
# Add database init script & run it
ADD tools/database_creation.sql /srv/jersey-skeleton/tools/
ADD tools/database_purge.sql /srv/jersey-skeleton/tools/
RUN sqlite3 /tmp/data.db < /srv/jersey-skeleton/tools/database_creation.sql

# Add database epuration scipt and run it via cron
ADD tools/crontab /etc/cron.d/database-cron
RUN chmod 0644 /etc/cron.d/database-cron
RUN touch /var/log/cron.log
CMD cron && tail -f /var/log/cron.log

# WEB SERVICE CONFIGURATION ====================================================
# Precise the source folder
ADD src /srv/QRcode-factory/src/
# Listen on the specified network port
EXPOSE 8080

# START THE WEB SERVER =========================================================
CMD mvn jetty:run
