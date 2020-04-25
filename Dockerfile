FROM openjdk:8-jdk-alpine
MAINTAINER Aibek Mazhitov (amazhitov@gmail.com)

EXPOSE 8080

ARG DB_USER
ARG DB_PASS
ARG DB_URL

ENV DB_USER=$DB_USER
ENV DB_PASS=$DB_PASS
ENV DB_URL=$DB_URL

ADD build/libs/backend-0.0.1.jar app.jar

ENTRYPOINT exec java -Dspring.flyway.user=$DB_USER -Dspring.flyway.password=$DB_PASS -Dspring.flyway.url=$DB_URL -Dspring.datasource.username=$DB_USER -Dspring.datasource.password=$DB_PASS -Dspring.datasource.url=$DB_URL -jar app.jar
