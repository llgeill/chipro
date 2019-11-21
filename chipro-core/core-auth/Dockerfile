FROM openjdk:8-jre-stretch
ARG JAR_FILE
COPY  ${JAR_FILE} app.jar
EXPOSE 1000

ENTRYPOINT ["java", "-jar","-Xms1024m","-Xmx1024m","-Xss1024K","-XX:MetaspaceSize=512m","-XX:MaxMetaspaceSize=512m","app.jar"]
CMD ["--spring.profiles.active=k8s"]


#需要编译源码时候用到
#FROM maven:3.6-jdk-8 AS appserver
#COPY maven_settings.xml /etc/maven/maven_settings.xml
#WORKDIR /app
#COPY guns-cloud-gateway /app
#RUN mvn -B -s /etc/maven/maven_settings.xml package -Dmaven.test.skip -P docker
#
#
#FROM openjdk:8-jre-stretch
#ARG JAR_FILE
#COPY  ${JAR_FILE} app.jar
#ENTRYPOINT ["java", "-jar","-Xms1024m","-Xmx1024m","-Xss1024K","-XX:MetaspaceSize=512m","-XX:MaxMetaspaceSize=512m","app.jar"]
#CMD ["--spring.profiles.active=k8s"]