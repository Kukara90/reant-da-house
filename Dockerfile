FROM openjdk:17.0.2 as build
COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .
COPY src src
RUN ./mvnw -B -DskipTests package
FROM openjdk:17.0.2
COPY --from=build target/rent-a-house-0.0.1-SNAPSHOT.jar rent-a-house-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/rent-a-house-0.0.1-SNAPSHOT.jar"]