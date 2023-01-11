# Using a machine ith Gradle installed. We call it builder
FROM gradle:7.5.1-jdk17-alpine AS builder

# make and cd into the directory /workspace/app
WORKDIR /workspace/app

# Copy stuff into the new machine so we can build
COPY build.gradle settings.gradle ./
COPY src src

# Build the application. This doesn't run the tests. It just builds it
RUN gradle build -x test

# On another machine that has Java 17 installed
FROM eclipse-temurin:17-alpine

# make and cd into the directory /app
WORKDIR /app

# Define parameters that can be supplied to the image when we call Docker build
ARG APP_PORT=8000
ARG PG_USER=admin
ARG PG_PASSWORD=admin
ARG PG_DATABASE=cms-db
ARG PG_HOST=localhost
ARG PG_PORT=5431
ARG KEYCLOAK_DB=keycloak-db
ARG KEYCLOAK_DB_USER=admin
ARG KEYCLOAK_DB_PASSWORD=admin
ARG KEYCLOAK_DB_PORT=5432
ARG KEYCLOAK_DB_HOST=localhost
ARG KEYCLOAK_PORT=8080
ARG KEYCLOAK_ADMIN_USER=admin
ARG KEYCLOAK_ADMIN_PASSWORD=password

# Define parameters that can be supplied to the image during build or during run
ENV APP_PORT=$APP_PORT
ENV PG_USER=$PG_USER
ENV PG_PASSWORD=$PG_PASSWORD
ENV PG_DATABASE=$PG_DATABASE
ENV PG_HOST=$PG_HOST
ENV PG_PORT=$PG_PORT
ENV KEYCLOAK_DB=$KEYCLOAK_DB
ENV KEYCLOAK_DB_USER=$KEYCLOAK_ADMIN_USER
ENV KEYCLOAK_DB_PASSWORD=$KEYCLOAK_DB_PASSWORD
ENV KEYCLOAK_DB_PORT=$KEYCLOAK_DB_PORT
ENV KEYCLOAK_DB_HOST=$KEYCLOAK_DB_HOST
ENV KEYCLOAK_PORT=$KEYCLOAK_PORT
ENV KEYCLOAK_ADMIN_USER=$KEYCLOAK_ADMIN_USER
ENV KEYCLOAK_ADMIN_PASSWORD=$KEYCLOAK_ADMIN_PASSWORD

# Copy the JAR file we made earlier from builder to the /app folder on the new machine
COPY --from=builder /workspace/app/build/libs/choose-my-snooze-0.0.1.jar .

# Expose the app port e just configured (not really needed as we will use host settings, but useful just in case we change our minds)
EXPOSE $APP_PORT

# The command that kicks off the program. We use entry point here as we don't want to prepend /bin/bash -c
# We want to call the java binary directly as it's on the path on the new machine
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "choose-my-snooze-0.0.1.jar"]