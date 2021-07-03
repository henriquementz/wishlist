FROM adoptopenjdk/maven-openjdk11 AS MAVEN_TOOL_CHAIN
COPY pom.xml /tmp/
COPY wishlist-api /tmp/wishlist-api/
COPY wishlist-core /tmp/wishlist-core/
COPY wishlist-dataprovider /tmp/wishlist-dataprovider/
WORKDIR /tmp/
RUN mvn package

FROM openjdk:11-jre-slim
COPY --from=MAVEN_TOOL_CHAIN /tmp/wishlist-api/target/*.jar /app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]