# base image which contains the Java runtime
FROM openjdk:17-slim as build

# information about the maintainer of the image
LABEL maintainer="Realdo Dias <rdias@realdiv.com>"

# add application Jar file to the Docker container
COPY target/*.jar app.jar

# execute the Jar file using Docker basic configuration
# to avoid this ==> java -jar target\accounts-0.0.1-SNAPSHOT.jar
ENTRYPOINT [ "java", "-jar", "/app.jar" ]
