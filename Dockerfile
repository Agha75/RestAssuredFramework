FROM maven:3.8.6-jdk-8
ADD target/restassured-framework-annualgoals.jar restassured-framework-annualgoals.jar
ADD . .
EXPOSE 8080
ENTRYPOINT ["mvn","test"]
