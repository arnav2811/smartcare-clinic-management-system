# Stage 1: Build the application
FROM eclipse-temurin:21-jdk-jammy AS build

# Set the working directory inside the container
WORKDIR /workspace/app

# Copy the Maven wrapper and pom.xml first to cache dependencies
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Make the wrapper executable (Windows file permissions sometimes get lost)
RUN chmod +x ./mvnw

# Download dependencies (this step is cached if pom.xml doesn't change)
RUN ./mvnw dependency:go-offline -B

# Copy the project source code
COPY src src

# Build the application, skipping tests to speed up the process
RUN ./mvnw clean package -DskipTests

# Stage 2: Create the production runtime image
FROM eclipse-temurin:21-jre-jammy

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /workspace/app/target/*.jar app.jar

# Expose port 8080 for the application
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
