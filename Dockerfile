# --- Etapa 1: Build ---
FROM gradle:8.5-jdk21-alpine AS build
WORKDIR /app

# Copiar los archivos de configuración de Gradle
COPY build.gradle settings.gradle ./

# Descargar dependencias
RUN gradle build -x test --no-daemon > /dev/null 2>&1 || true

# Copiar código fuente y compilar
COPY src ./src
RUN gradle clean bootJar -x test --no-daemon

# --- Etapa 2: Run ---
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Usuario no root
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Copiar el JAR desde la etapa de construcción
COPY --from=build /app/build/libs/*.jar app.jar

ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -Djava.security.egd=file:/dev/./urandom"

# Exponer el puerto
EXPOSE 8080

# Ejecutar la aplicación
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]