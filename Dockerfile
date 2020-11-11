FROM openjdk:11
ADD target/app.jar app.jar
EXPOSE 4000
ENTRYPOINT ["java", "-jar","app.jar"]