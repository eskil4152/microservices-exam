FROM amazoncorretto:17
COPY ./target/ItemService-0.0.1-SNAPSHOT.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java","-jar","ItemService-0.0.1-SNAPSHOT.jar"]
EXPOSE 8084