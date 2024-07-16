FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/pruebatecnica-0.0.1.jar
COPY ${JAR_FILE} pruebatecnica.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "pruebatecnica.jar"]