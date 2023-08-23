FROM amazoncorretto:17
COPY ./target/DiscoveryService-0.0.1-SNAPSHOT.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java","-jar","DiscoveryService-0.0.1-SNAPSHOT.jar"]
EXPOSE 8761