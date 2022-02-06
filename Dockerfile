FROM openjdk:17.0.2
COPY target/rent-a-house-0.0.1-SNAPSHOT.jar rent-a-house-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/rent-a-house-0.0.1-SNAPSHOT.jar"]