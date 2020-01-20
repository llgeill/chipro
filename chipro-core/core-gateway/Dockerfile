FROM openjdk:8-jre-stretch
ARG JAR_FILE
COPY  ${JAR_FILE} app.jar
EXPOSE 4000

ENTRYPOINT ["java", "-jar","-Xms256m","-Xmx256m","-Xss256K","-XX:MetaspaceSize=256m","-XX:MaxMetaspaceSize=256m","app.jar"]
#CMD ["--spring.profiles.active=k8s"]


