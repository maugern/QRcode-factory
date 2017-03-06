# QRcode-factory's Dockerfile ==================================================
# Release under MIT Licence
# For more information, github.com/maugern/QRcode-factory

# DEFINE IMAGE =================================================================
FROM debian:jessie
MAINTAINER Nicolas Mauger <https://github.com/maugern/>

# BEFORE INSTALL ===============================================================
# Sets language to UTF8 : this works in pretty much all cases
ENV LANG en_US.UTF-8
# Add PostgreSQL's repository
RUN apt-key adv --keyserver hkp://p80.pool.sks-keyservers.net:80 --recv-keys B97B0AFCAA1A47F044F244A07FCC7D46ACCC4CF8
RUN echo "deb http://apt.postgresql.org/pub/repos/apt/ precise-pgdg main" > /etc/apt/sources.list.d/pgdg.list

# INSTALL JAVA 7 & MAVEN & Postgresql ==========================================
RUN apt-get update && \
    apt-get install --fix-missing -y \
            openjdk-7-jdk \
            maven \
            python-software-properties \
            software-properties-common \
            postgresql-9.3 \
            postgresql-client-9.3 \
            postgresql-contrib-9.3

# CONFIGURE JAVA ===============================================================
ENV JAVA_HOME /usr/lib/jvm/java-1.7.0-openjdk-amd64
ENV PATH $PATH:$JAVA_HOME/bin
ENV CLASSPATH $JAVA_HOME/lib/tools.jar

# CONFIGURE MAVEN ==============================================================
ADD pom.xml /srv/QRcode-factory/
WORKDIR /srv/QRcode-factory/
RUN mvn install

# Postgresql configuration & database ==========================================
USER postgres
RUN /etc/init.d/postgresql start && \
    psql --command "CREATE USER docker WITH SUPERUSER PASSWORD 'docker';" &&\
    createdb -O docker docker

# Adjust PostgreSQL configuration so that remote connections to the
# database are possible.
RUN echo "host all  all    0.0.0.0/0  md5" >> /etc/postgresql/9.3/main/pg_hba.conf

# And add ``listen_addresses`` to ``/etc/postgresql/9.3/main/postgresql.conf``
RUN echo "listen_addresses='*'" >> /etc/postgresql/9.3/main/postgresql.conf

# Expose the PostgreSQL port
EXPOSE 5433

# Add VOLUMEs to allow backup of config, logs and databases
VOLUME  ["/etc/postgresql", "/var/log/postgresql", "/var/lib/postgresql"]

# Set the default command to run when starting the container
CMD ["/usr/lib/postgresql/9.3/bin/postgres", "-D", "/var/lib/postgresql/9.3/main", "-c", "config_file=/etc/postgresql/9.3/main/postgresql.conf"]

# WEB SERVICE CONFIGURATION ====================================================
# Precise the source folder
ADD src /srv/QRcode-factory/src/
# Listen on the specified network port
EXPOSE 8080

# START THE WEB SERVER =========================================================
USER root
CMD mvn jetty:run
