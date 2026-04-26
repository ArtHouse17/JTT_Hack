# Dockerfile
FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

# Сначала копируем pom.xml для кэширования зависимостей
COPY pom.xml .
RUN mvn dependency:go-offline

# Затем копируем исходники
COPY src ./src

# Собираем приложение
RUN mvn clean package -DskipTests

# Runtime-образ
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]