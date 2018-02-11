FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
ENV CASSANDRA_CONTACT_POINTS=""
ADD ${JAR_FILE} app.jar
EXPOSE 8093
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar","--cassandra.contact-points=${CASSANDRA_CONTACT_POINTS}"]