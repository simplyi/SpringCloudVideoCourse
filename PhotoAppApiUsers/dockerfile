FROM openjdk:8-jdk-alpine 
VOLUME /tmp 
COPY target/PhotoAppApiUsers-0.0.1-SNAPSHOT.jar users-microservice.jar 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/users-microservice.jar"] 