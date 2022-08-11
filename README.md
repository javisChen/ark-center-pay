# 介绍
kt-cloud-pay

# 运行

## Docker Build
```shell
docker build -f ./kt-cloud-pay-start/Dockerfile -t kt-cloud-pay:v1 ./kt-cloud-pay-start
```
## Docker Run
```shell
docker run -p 8083:8083 --name kt-cloud-pay -d \
-e NACOS_DISCOVERY_IP=172.24.80.20 \
-e NACOS_DISCOVERY_SERVER_ADDR=172.24.80.20:8848 \
-e NACOS_CONFIG_SERVER_ADDR=172.24.80.20:8848 \
-e SYS_OPT=-DSpring.profiles.active=dev \
kt-cloud-pay:v1
```

# 软件架构

## 组件说明

| 组件名称                       | 描述           | 版本    |
|----------------------------|--------------|-------|
| kt-cloud-pay-acl     | 防腐层          | 1.0.0 |
| kt-cloud-pay-api     | 开放接口层        | 1.0.0 |
| kt-cloud-pay-dao     | 数据访问层        | 1.0.0 |
| kt-cloud-pay-manager | 通用业务处理层      | 1.0.0 |
| kt-cloud-pay-service | 具体业务以及内部访问转发 | 1.0.0 |
| kt-cloud-pay-start   | 启动层          | 1.0.0 |


## 使用说明
```
mvn install到本地（如果不想运行测试，可以加上-DskipTests参数）
```