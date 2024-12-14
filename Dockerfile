FROM amazoncorretto:21-alpine3.19-full
EXPOSE 8080
ADD target/modern-distributed-systems-hft-24-project-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]