FROM adoptopenjdk/openjdk11:alpine-jre
EXPOSE 8080
# Refer to Maven build -> finalName
ARG JAR_FILE=target/vehicle_management.jar

# cd /opt/app
WORKDIR /opt/app

# cp target/vehicle_management.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar

# java -jar /app/app.jar
CMD ["java","-jar","app.jar"]
