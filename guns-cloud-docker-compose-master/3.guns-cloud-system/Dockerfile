FROM maven:3.6-jdk-8 AS appserver
COPY maven_settings.xml /etc/maven/maven_settings.xml
WORKDIR /app
COPY guns-cloud-system /app
RUN mvn -B -s /etc/maven/maven_settings.xml package -Dmaven.test.skip -P docker


FROM openjdk:8-jre-stretch
WORKDIR /app
COPY --from=appserver /app/guns-cloud-system-app/target/guns-cloud-system-app.jar .

ENV SERVER_PORT   8001
ENV REGISTER_HOST guns-cloud-nacos
ENV DB_URL        jdbc:mysql://guns-cloud-mysql:3306/guns_cloud_system?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&serverTimezone=UTC
ENV DB_USERNAME   root
ENV DB_PASSWORD   root

ENTRYPOINT ["wait-for-it.sh", "guns-cloud-nacos:8848", "--timeout=0", "--", "java", "-jar","-Xms1024m","-Xmx1024m","-Xss1024K","-XX:MetaspaceSize=512m","-XX:MaxMetaspaceSize=512m","/app/guns-cloud-system-app.jar"]
CMD ["--spring.profiles.active=docker"]