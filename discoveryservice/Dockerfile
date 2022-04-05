FROM openjdk:8-jdk
EXPOSE 8761
ADD target/discovery-docker.jar discovery-docker.jar
ENTRYPOINT ["java","-jar","/discovery-docker.jar"]