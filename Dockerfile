FROM openjdk:17-jdk-alpine
MAINTAINER remmo1
COPY target/converter-0.0.1-SNAPSHOT.jar converter-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","converter-0.0.1-SNAPSHOT.jar"]

# docker build --tag=remmo1/currency-converter-spring:0.0.1-SNAPSHOT .
