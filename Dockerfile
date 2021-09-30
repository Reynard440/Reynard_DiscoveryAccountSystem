FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD DiscoveryAccountSystemWebSpringBoot/target/DiscoveryAccountSystemWebSpringBoot-1.0-SNAPSHOT.war app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

# From tomcat:8.0.51-jre8-alpine
# CMD ["catalina.sh","run"]