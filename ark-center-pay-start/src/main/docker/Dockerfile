FROM openjdk:17-oraclelinux8

RUN microdnf install -y iputils

WORKDIR /app

COPY ark-center-pay.jar /app

EXPOSE 8086

ENV SYS_OPT=""

ENTRYPOINT java $SYS_OPT -jar /app/ark-center-pay.jar
