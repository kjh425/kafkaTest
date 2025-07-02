# Kafka Practice Project (Spring Boot + Docker Compose) 카프카 실습예제입니다.

## 구성

kafkaTest/
├── A/ # Spring Boot App A (Producer)
│ ├── Dockerfile
│ └── build/libs/_.jar
├── B/ # Spring Boot App B (Consumer)
│ ├── Dockerfile
│ └── build/libs/_.jar
├── C/ # Spring Boot App C (Consumer)
│ ├── Dockerfile
│ └── build/libs/\*.jar
├── docker-compose.yml
└── README.md

각 프로젝트(A,B,C)에 들어간 후
./gradlew build 로 jar를 먼저 만든 후
compose를 이용해서 컨테이너를 전부 한번에 띄움

(종료)
docker-compose down -v --remove-orphans
(볼륨 초기화)
docker volume prune -f
(시작)
docker-compose up --build -d

- Kafka (KRaft 모드)
- Spring Boot 앱 3개
  - `App A`: 메시지 생산 (Producer)
  - `App B`, `App C`: 메시지 소비 (Consumer)

## 실행 방법

docker-compose up --build -d

## Kafka 토픽 생성

docker exec -it kafkatest-kafka-1 bash

# 토픽 생성

/opt/bitnami/kafka/bin/kafka-topics.sh --create \
 --topic user-topic \
 --bootstrap-server localhost:9092 \
 --partitions 1 \
 --replication-factor 1

# Producer 실행

docker exec -it kafkatest-kafka-1 bash

컨테이너로 들어간후 !!

/opt/bitnami/kafka/bin/kafka-console-producer.sh \
 --bootstrap-server localhost:9092 \
 --topic user-topic

# Consumer 실행

docker exec -it kafkatest-kafka-1 bash

컨테이너로 들어간후 !!

/opt/bitnami/kafka/bin/kafka-console-consumer.sh \
 --bootstrap-server localhost:9092 \
 --topic user-topic \
 --from-beginning

# test endPoint 8081포트 /send

위 요청을 날리게되면 B와 C에서 user 50명을 받아 찍어내게 됩니다.

Producer에서 메시지 날리면 순차로 Consumer에서 받게됨. 터미널에서 실제로 메시지 받는거 확인가능한 프로젝트 실습입니다.
