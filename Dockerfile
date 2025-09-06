# # Use an official OpenJDK runtime as a parent image
# FROM eclipse-temurin:17-jdk-jammy
#
# # Set the working directory inside the container
# WORKDIR /app
#
# # Copy the built jar file into the container
# COPY target/notesapp-0.0.1-SNAPSHOT.jar app.jar
#
# # Expose the port your Spring Boot app runs on (default 8080)
# EXPOSE 8080
#
# # Run the jar file
# ENTRYPOINT ["java", "-jar", "app.jar"]


# Stage 1: Build the app
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run the app
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY --from=build /app/target/notesapp-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
