FROM openjdk:8-jre-alpine

RUN apk update && apk add --no-cache gcompat

COPY target/scala-2.12/ta-daily-job-assembly-0.1.jar /ta-daily-job.jar

CMD ["java", "-jar", "/ta-daily-job.jar"]