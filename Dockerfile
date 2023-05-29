FROM openjdk:17-ea-10-jdk-alpine
LABEL maintainer="Brody Gaudel https://github.com/BrodyGaudel"
COPY target/gestion-employee-0.0.1.jar gestion-employee-0.0.1.jar
ENTRYPOINT ["java","-jar","gestion-employee-0.0.1.jar"]