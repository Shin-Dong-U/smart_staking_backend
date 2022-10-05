FROM maven:3.8.6-jdk-8 as builder
COPY . .
RUN mvn clean package

FROM openjdk:8-jre
ARG JAR_FILE=target/*.war
COPY --from=builder ${JAR_FILE} app.war
ENTRYPOINT ["java", "D-spring.profiles.active=prod", "-jar", "app.war"]