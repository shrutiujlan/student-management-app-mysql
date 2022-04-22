FROM maven:3.8.2-jdk-8
WORKDIR /project
COPY . .
ADD target/project-0.0.1-SNAPSHOT.jar app.jar
RUN mvn clean install
CMD mvn spring-boot:run
ENTRYPOINT ["java","-jar","/app.jar", "--spring.profiles.active=docker"]