FROM eclipse-temurin:17

LABEL mentainer="rayen.naseer.dev@gmail.com"

WORKDIR /app

COPY target/springboot-restful-webservices-0.0.1-SNAPSHOT.jar /app/springboot-restful-webservices

ENTRYPOINT ["java", "-jar", "springboot-restful-webservices"]