FROM eclipse-temurin:21-jdk-alpine as build


WORKDIR /app/backend


COPY mvnw .
COPY .mvn/ .mvn
COPY pom.xml .
RUN chmod +x mvnw
RUN ./mvnw dependency:resolve
COPY src ./src
EXPOSE 9898
CMD ["./mvnw", "spring-boot:run"]
