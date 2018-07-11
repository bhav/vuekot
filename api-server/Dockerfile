FROM openjdk:8-jdk
COPY . /app

WORKDIR /app

RUN ./gradlew -v

EXPOSE 8080


CMD ./gradlew -t build & ./gradlew runDebug