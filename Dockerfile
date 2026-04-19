FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/api-eventos.jar api-eventos.jar
ENTRYPOINT ["java", "-jar", "api-eventos.jar"]