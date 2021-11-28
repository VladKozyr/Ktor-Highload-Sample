## Highload practive #4

> Розробити два API-ендпойнти: один для отримання даних, другий для запису.
> - дані потрібно зберігати в базі даних.
> - Читати треба через кеш.
> - Писати через чергу.
> - Результат має запускатись через docker-compose; містити інструкцію по налаштуванню і запуску.
>
> Базу даних, брокер черг, кеш та мову програмування можна використовувати будь-які.

### Start microservice

```
docker-compose up
```

### Stack

- Ktor
- Exchanged
- Postgresql
- Redis (Jedis)
- RabbitMQ

### Entity -> note

```json
{
  "id": 1,
  "title": "",
  "description": ""
}
```

### Testing
- Access at [URL](http://localhost:8080)
- REDIS_PORT=6379
- [RabbitMQ manager](http://localhost:15672/)
   - RABBITMQ_PORT=5672
   - RABBITMQ_MANAGER_PORT=15672
- [Postman collection](/KMA_HIGHLOAD.postman_collection.json)
- POSTGRES_PORT=5432
   - POSTGRES_DB=ktor
   - POSTGRES_USER=username
   - POSTGRES_PASSWORD=secret
