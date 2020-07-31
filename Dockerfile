FROM maven:3.6.1-jdk-11-slim AS build-maven

ARG versao="1.0.0"

WORKDIR /build

COPY . ./

RUN echo "${VERSAO}"

RUN mvn versions:set -DnewVersion="${VERSAO}"

RUN mvn clean package -DskipTests

FROM adoptopenjdk/openjdk11-openj9:alpine-slim

WORKDIR /app

VOLUME /tmp

COPY --from=build-maven /build/target/challenge-back-phael*.jar ./challenge-back-phael.jar

ENV SPRING_PROFILES_ACTIVE="prod" \
	LANG="pt_BR.UTF-8" \
    LANGUAGE="pt_BR:pt" \
    LC_ALL="pt_BR.UTF-8"

ENTRYPOINT ["java",  "-jar", "/app/challenge-back-phael.jar", "--spring.profiles.active=${SPRING_PROFILES_ACTIVE}"]