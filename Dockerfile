#
# Build stage
#
FROM maven:3.8.3-openjdk-17 AS build

# Copy everything from your local project directory to /home/app in the container
COPY . /home/app

# Run the Maven build inside the container
RUN mvn -f /home/app/pom.xml clean package -DskipTests

# Use a smaller base image for running the app
FROM openjdk:17
COPY --from=build /home/app/target/*.jar /home/app/todoapp.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/home/app/todoapp.jar"]