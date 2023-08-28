# Use an official Maven and Java 8 runtime as the base image
FROM maven:3.8.4-openjdk-8-slim AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the source code into the container
COPY . .

# Build the application using Maven
RUN --mount=type=cache,target=/root/.m2 mvn clean package -DskipTests

# Create a new stage for the final image
FROM openjdk:8-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the executable JAR file from the build stage
COPY --from=build /app/target/*.jar ./app.jar

# Expose the port on which your Spring Boot application runs (change if necessary)
EXPOSE 8080

# Set the entry point to run your application using java -jar command
ENTRYPOINT ["java", "-jar", "app.jar"]
