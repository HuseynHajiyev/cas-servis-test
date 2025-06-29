FROM gradle:8.7.0-jdk21 AS builder
WORKDIR /app
COPY --chown=gradle:gradle . .
RUN gradle clean build -x test --no-daemon

FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar .
RUN mv *.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
