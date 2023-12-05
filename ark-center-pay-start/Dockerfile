FROM openjdk:17-oraclelinux8

RUN microdnf install -y iputils

WORKDIR /app
COPY ./target/ark-center-pay.jar /app
EXPOSE 8080
ENV SYS_OPT=""
ENTRYPOINT java $SYS_OPT -jar /app/ark-center-pay.jar
