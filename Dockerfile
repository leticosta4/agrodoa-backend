FROM maven:3.9.9-eclipse-temurin-21

WORKDIR /opt/app

COPY . /opt/app

CMD [ "mvn", "spring-boot:run" ]
