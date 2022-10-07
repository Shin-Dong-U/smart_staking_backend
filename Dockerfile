FROM maven:3.8.6-jdk-8 as builder
COPY . .
RUN mvn clean package

FROM openjdk:8-jre
ARG JAR_FILE=target/*.jar
COPY --from=builder ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]