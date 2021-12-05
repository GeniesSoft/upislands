FROM openjdk:16
VOLUME /tmp
COPY target/backend-1.0.jar app-server.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=compose", "app-server.jar"]