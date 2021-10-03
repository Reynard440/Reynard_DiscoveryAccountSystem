FROM openjdk:8
ADD DiscoveryAccountSystemWebSpringBoot/target/DiscoveryAccountSystemWebSpringBoot-1.0-SNAPSHOT.war discoveryaccountsystem.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "discoveryaccountsystem.jar"]

#FROM openjdk:8
#COPY DiscoveryAccountSystemRepository/target/DiscoveryAccountSystemRepository-1.0-SNAPSHOT.jar app2.jar
#CMD ["java","-jar","/app2.jar"]

# From tomcat:8.0.51-jre8-alpine
# CMD ["catalina.sh","run"]