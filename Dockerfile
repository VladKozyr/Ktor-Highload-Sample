FROM openjdk:12-jdk-alpine

RUN apk add --no-cache bash

WORKDIR /ktor-highload

COPY . /ktor-highload

ENTRYPOINT ["sh", "docker/docker-entrypoint.sh"]

CMD ./gradlew run