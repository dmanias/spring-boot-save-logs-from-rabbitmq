# Use the official maven/Java 8 image to create a build artifact.
FROM maven:3.8.2-openjdk-11-slim AS build

# Set the current working directory in the image
WORKDIR /app

# Copy the pom.xml file
COPY ./pom.xml ./pom.xml

# Build all dependencies for offline use
RUN mvn dependency:go-offline -B

# Copy your other files
COPY ./src ./src

# Build the project and package it as a jar
RUN mvn package -DskipTests

# The final stage to create the final image
FROM openjdk:11-jdk-slim

# Set the current working directory in the image
WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
