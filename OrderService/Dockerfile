FROM amazoncorretto:17
COPY ./target/OrderService-0.0.1-SNAPSHOT.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java","-jar","OrderService-0.0.1-SNAPSHOT.jar"]
EXPOSE 8081