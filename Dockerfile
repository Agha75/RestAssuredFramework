FROM maven:3.8.6-jdk-8
ADD . .
EXPOSE 8080
ENTRYPOINT ["mvn","test"]
