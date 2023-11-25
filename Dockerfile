FROM openjdk:17-jdk-slim

ADD target/k-service-0.0.1-SNAPSHOT.jar app.jar
#CMD apt update -y && apt install inetutils-ping -y && apt install redis-server -y && apt install telnet
ENTRYPOINT ["java","-jar","app.jar"]
#ENTRYPOINT ["java","-Xms512m","-Xmx1g","-jar","app.jar"]