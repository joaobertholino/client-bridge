FROM maven:3.9.9-eclipse-temurin-21 AS build
COPY src /client-bridge-build/src
COPY pom.xml /client-bridge-build
WORKDIR /client-bridge-build
RUN mvn clean package

FROM openjdk:21
COPY --from=build /client-bridge-build/target/*.jar /client-bridge-image/app.jar
WORKDIR /client-bridge-image
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]