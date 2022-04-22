FROM openjdk:8-jdk-alpine
ADD target/project-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar", "--spring.profiles.active=docker"]