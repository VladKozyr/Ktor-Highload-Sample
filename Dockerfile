FROM openjdk:12-jdk-alpine

RUN apk add --no-cache bash

WORKDIR /ktor-highload

COPY docker/docker-entrypoint.sh /

ENTRYPOINT ["sh", "/docker-entrypoint.sh"]

CMD ./gradlew run