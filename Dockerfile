FROM openjdk:8-jdk-alpine
MAINTAINER lanaforge.ca
COPY target/docker-feature-toggle-api-0.0.2.jar feature-toggle-api.jar
ENTRYPOINT ["java","-jar","/feature-toggle-api.jar"]
