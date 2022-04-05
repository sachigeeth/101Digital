FROM openjdk:8-jdk
ADD target/operation-docker.jar operation-docker.jar
EXPOSE 9999:9999
ENTRYPOINT ["java","-jar","/operation-docker.jar"]
