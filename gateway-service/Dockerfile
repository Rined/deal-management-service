FROM maven:3.5.4-jdk-8

ENV PROJECT_DIR=/opt/project
RUN mkdir -p $PROJECT_DIR
WORKDIR $PROJECT_DIR

ADD gateway-service/pom.xml $PROJECT_DIR
ADD gateway-service/src/ $PROJECT_DIR/src
RUN mvn package -DskipTests

FROM openjdk:8-jdk-alpine

ENV PROJECT_DIR=/opt/project
RUN mkdir -p $PROJECT_DIR
WORKDIR $PROJECT_DIR

COPY --from=0 $PROJECT_DIR/target/gateway-service.jar $PROJECT_DIR/

EXPOSE 8083

CMD ["java", "-jar", "/opt/project/gateway-service.jar"]