# Step 1: Use a lightweight JDK base image
FROM openjdk:21-jdk-slim

# Step 2: Set the working directory inside container
WORKDIR /app

# Step 3: Copy the jar file into the container
COPY target/*.jar app.jar

# Step 4: Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]