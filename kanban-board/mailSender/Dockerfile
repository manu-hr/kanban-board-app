FROM openjdk

WORKDIR usr/lib

ADD ./target/mailSender-0.0.1-SNAPSHOT.jar /usr/lib/mailSender-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","mailSender-0.0.1-SNAPSHOT.jar"]