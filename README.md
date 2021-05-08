# Microservice-Ribbon-Circuitbreaker-Sleuth

```
Run 2 instances of Microservice 2 with port no. 8200 and 8201 and try to hit get API of Micro service 1 to see round robin routing done by Ribbon.

```
## For Redis Cache Server
```
docker run -v /Users/anujparmar/Desktop/Study/docker-deploy/mysql/redisdata:/usr/local/etc/redis  --name some-redis -d -p 6379:6379 redis:buster redis-server  --appendonly yes

```
