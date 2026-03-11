FROM maven:3.8.5-openjdk-17
WORKDIR /app
COPY . .
# Le decimos que entre a la carpeta de tu proyecto
WORKDIR /app/inventory-service
RUN mvn clean package -DskipTests
EXPOSE 8080
CMD ["java", "-jar", "target/inventory-service-0.0.1-SNAPSHOT.jar"]
