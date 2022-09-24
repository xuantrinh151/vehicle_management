# syntax= docker/dockerfile:1

FROM adoptopenjdk/openjdk11

# Refer to Maven build -> finalName
ARG JAR_FILE=target/vehicle_management.jar

WORKDIR /app

# cp target/vehicle_management.jar /opt/app/app.jar
COPY .mvn/ .mvn
COPY  mvnw pom.xml ./

#Run
RUN ./mvnw dependency:go-offline

COPY src ./src
# java -jar /app/app.jar
CMD ["./mvnw","spring-boot:run"]
