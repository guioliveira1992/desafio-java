FROM openjdk:8-jdk-alpine
COPY target/desafio-java-0.0.1-SNAPSHOT.jar desafio-java-0.0.1-SNAPSHOT.jar
VOLUME /tmp
ENTRYPOINT ["java","-jar","/desafio-java-0.0.1-SNAPSHOT.jar"]