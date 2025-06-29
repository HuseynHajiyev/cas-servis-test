FROM gradle:8.7.0-jdk21 AS builder
WORKDIR /app
COPY --chown=gradle:gradle . .
RUN gradle clean build -x test --no-daemon

# Stage 2: Run with JDK only
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=builder /app/build/libs/casServis-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
