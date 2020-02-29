FROM openjdk:8-jdk

EXPOSE 8081

VOLUME /tmp
ADD maven/dito-consumer-0.0.1-SNAPSHOT.jar myapp.jar
RUN sh -c 'touch /myapp.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/myapp.jar"]