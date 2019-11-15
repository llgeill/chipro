FROM maven:3.6-jdk-8 AS appserver
COPY maven_settings.xml /etc/maven/maven_settings.xml
WORKDIR /app
COPY guns-cloud-gateway /app
RUN mvn -B -s /etc/maven/maven_settings.xml package -Dmaven.test.skip -P docker


FROM openjdk:8-jre-stretch
WORKDIR /app
COPY --from=appserver /app/target/guns-cloud-gateway.jar .

ENV SERVER_PORT         8000
ENV REGISTER_HOST       guns-cloud-nacos
ENV REGISTER_HOST_PORT  guns-cloud-nacos:8848

ENTRYPOINT ["wait-for-it.sh", "guns-cloud-system:8001", "--timeout=0", "--", "java", "-jar","-Xms1024m","-Xmx1024m","-Xss1024K","-XX:MetaspaceSize=512m","-XX:MaxMetaspaceSize=512m","/app/guns-cloud-gateway.jar"]
CMD ["--spring.profiles.active=docker"]