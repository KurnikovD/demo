FROM maven:3.8.3
COPY src /app/src
COPY pom.xml /app
RUN mvn -f /app/pom.xml clean package

FROM openjdk:17
WORKDIR /app
COPY --from=0 /app/target/*.jar DemoApp.jar
ENTRYPOINT ["java","-jar","DemoApp.jar"]