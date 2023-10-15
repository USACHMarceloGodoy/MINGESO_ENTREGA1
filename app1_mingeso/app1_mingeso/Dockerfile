FROM openjdk:19
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} WebAppPEP1.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","/WebAppPEP1.jar"]