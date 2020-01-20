FROM openjdk:8-jre-stretch
ARG JAR_FILE
COPY  ${JAR_FILE} app.jar
EXPOSE 1026

ENTRYPOINT ["java", "-jar","-Xms1024m","-Xmx1024m","-Xss1024K","-XX:MetaspaceSize=512m","-XX:MaxMetaspaceSize=512m","app.jar"]
CMD ["--spring.profiles.active=k8s"]


