FROM openjdk:11
COPY back/target/spring-0.0.1-SNAPSHOT.jar spring-app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "spring-app.jar"]