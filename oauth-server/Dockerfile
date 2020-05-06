FROM maven:3-openjdk-11 AS MAVEN_BUILD
MAINTAINER Marco Yuquilima
COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn package -DskipTests
FROM openjdk:11
WORKDIR /app
EXPOSE 9100
COPY --from=MAVEN_BUILD /build/target/oauth-server-0.0.1-SNAPSHOT.jar /app/
ENTRYPOINT ["java", "-jar", "oauth-server-0.0.1-SNAPSHOT.jar"]