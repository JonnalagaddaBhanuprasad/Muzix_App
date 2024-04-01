#FROM ubuntu:latest
#LABEL authors="BHANU PRASAD"
#
#ENTRYPOINT ["top", "-b"]
FROM openjdk
WORKDIR user/lib
ADD ./target/SpringBootMovieApplication-0.0.1-SNAPSHOT.jar /user/lib/SpringBootMovieApplication-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","SpringBootMovieApplication-0.0.1-SNAPSHOT.jar"]
