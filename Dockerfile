# QRcode-factory's Dockerfile ==================================================
# Release under MIT Licence
# For more information, https://github.com/maugern/QRcode-factory

# DEFINE IMAGE =================================================================
# 9.1 version is the latest supported by maven central jdbc
FROM postgres:9.1
MAINTAINER Nicolas Mauger <https://github.com/maugern/>

# BEFORE INSTALL ===============================================================
# Sets language to UTF8 : this works in pretty much all cases
ENV LANG en_US.UTF-8

# INSTALL JAVA 7 & MAVEN =======================================================
RUN apt-get update && \
    apt-get install --fix-missing -y \
            openjdk-7-jdk \
            maven

# CONFIGURE JAVA ===============================================================
ENV JAVA_HOME /usr/lib/jvm/java-1.7.0-openjdk-amd64
ENV PATH $PATH:$JAVA_HOME/bin
ENV CLASSPATH $JAVA_HOME/lib/tools.jar

# CONFIGURE MAVEN ==============================================================
ADD pom.xml /srv/QRcode-factory/
WORKDIR /srv/QRcode-factory/
RUN mvn install

# CONFIGURE POSTGRESQL =========================================================
RUN /etc/init.d/postgresql start
RUN psql --command "CREATE USER docker WITH SUPERUSER PASSWORD 'docker';" && \
    createdb -O docker docker

# Add scripts who will be executed before starting service
COPY tools/*.sql /docker-entrypoint-initdb.d/
COPY tools/*.sh  /docker-entrypoint-initdb.d/

# Add database epuration scipts and run it via cron
ADD tools/crontab /etc/cron.d/database-cron
RUN chmod 0644 /etc/cron.d/database-cron
RUN touch /var/log/cron.log
CMD cron && tail -f /var/log/cron.log

# WEB SERVICE CONFIGURATION ====================================================
# Precise the source folder
ADD src /srv/QRcode-factory/src/
EXPOSE 8080
# START THE WEB SERVER =========================================================

CMD mvn jetty:run
