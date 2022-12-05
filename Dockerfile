FROM gradle:7.5.1-jdk17-alpine AS builder
WORKDIR /workspace/app
COPY build.gradle settings.gradle ./
COPY src src

RUN gradle build -x test

