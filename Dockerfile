FROM openjdk:15
ADD target/springDemo-0.0.1-SNAPSHOT.jar fake-itunes-thymeleaf.jar
ENTRYPOINT [ "java", "-jar",  "fake-itunes-thymeleaf.jar"]
