FROM openjdk:11
WORKDIR /
ADD target/*.jar app.jar
EXPOSE 8090
CMD java -jar -Dspring.profiles.active=prod app.jar