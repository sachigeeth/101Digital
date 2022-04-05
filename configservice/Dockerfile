FROM openjdk:8-jdk
EXPOSE 8888
ADD target/config-docker.jar config-docker.jar
ENTRYPOINT ["java","-jar","/config-docker.jar"]