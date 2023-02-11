FROM eclipse-temurin:11-jdk-alpine
VOLUME /tmp
RUN mkdir /opt/app
COPY target/rent-da-house-resource-service*.jar /opt/app/app.jar

ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /opt/app/app.jar ${0} ${@}"]