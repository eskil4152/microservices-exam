FROM amazoncorretto:17
COPY ./target/GatewayService-0.0.1-SNAPSHOT.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java","-jar","GatewayService-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080