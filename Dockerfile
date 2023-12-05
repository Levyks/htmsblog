FROM maven:3-eclipse-temurin-21-alpine AS build
WORKDIR /usr/src/app

COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN --mount=type=cache,target=/.m2 mvn -Dmaven.repo.local=/.m2 install -DskipTests

FROM eclipse-temurin:21-alpine AS runtime
WORKDIR /usr/src/app

COPY --from=build /usr/src/app/target/*.jar app.jar

CMD ["java", "-jar", "app.jar"]