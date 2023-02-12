FROM maven:3.8.7-eclipse-temurin-11 AS MAVEN_BUILD
COPY pom.xml .
COPY rent-da-house-resource-service/src/ rent-da-house-resource-service/src/
COPY rent-da-house-resource-service/pom.xml rent-da-house-resource-service/.
RUN mvn clean package -Dmaven.test.skip

FROM eclipse-temurin:11-jdk-alpine
VOLUME /tmp
RUN mkdir /opt/app
COPY --from=MAVEN_BUILD rent-da-house-resource-service/target/rent-da-house-resource-service*.jar /opt/app/app.jar

ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /opt/app/app.jar ${0} ${@}"]