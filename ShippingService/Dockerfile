FROM amazoncorretto:17
COPY ./target/ShippingService-0.0.1-SNAPSHOT.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java","-jar","ShippingService-0.0.1-SNAPSHOT.jar"]
EXPOSE 8083