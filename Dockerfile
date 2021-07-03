FROM adoptopenjdk/maven-openjdk11 AS build

COPY pom.xml /tmp/
COPY wishlist-api /tmp/wishlist-api/
COPY wishlist-core /tmp/wishlist-core/
COPY wishlist-dataprovider /tmp/wishlist-dataprovider/
WORKDIR /tmp/

RUN mvn dependency:go-offline

RUN mvn package

FROM adoptopenjdk/openjdk11:alpine as release

COPY --from=build /tmp/wishlist-api/target/*.jar /app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]