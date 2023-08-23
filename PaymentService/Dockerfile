FROM amazoncorretto:17
COPY ./target/PaymentService-0.0.1-SNAPSHOT.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java","-jar","PaymentService-0.0.1-SNAPSHOT.jar"]
EXPOSE 8082