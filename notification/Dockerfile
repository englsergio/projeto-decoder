#
# build stage
#
FROM eclipse-temurin:21.0.2_13-jdk-alpine AS build
ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
ADD . $HOME
RUN --mount=type=cache,target=/root/.m2 ./mvnw -f $HOME/pom.xml clean package -DskipTests

#
# package stage
#
FROM eclipse-temurin:21.0.2_13-jre-alpine
LABEL authors="lsalmeida"
WORKDIR usr/app
ARG JAR_FILE=/usr/app/target/*.jar
COPY --from=build $JAR_FILE /app.jar
EXPOSE 8084
ENTRYPOINT ["java", "-Duser.timezone=America/Sao_Paulo", "-jar", "/app.jar"]