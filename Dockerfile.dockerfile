FROM java:8
RUN mkdir /testedocker
COPY target/analiseCredito-0.0.1-SNAPSHOT.jar /testedocker
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/testedocker/analiseCredito-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080
