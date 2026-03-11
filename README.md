Контейниризовал, настроил, можешь спокойно запустить с докером

**Пользователи:**


Создать юзера
```bash
curl -X POST http://localhost:8080/users -H "Content-Type: application/json" -d '{"username":"test","password":"123"}'
```

Получить пользователя по айди (имя, посты, комментарии)
```bash
curl -X GET http://localhost:8080/users?id=1
```

Удалить пользователя по айди
```bash
curl -X DELETE http://localhost:8080/users/1
```


**Посты:**


Создать пост
в curl запросе должно стоять помимо существующего уже юзера, его логин и пароль

```bash
curl -u test:123 -X POST "http://localhost:8080/posts?userId=1" -H "Content-Type: application/json" -d '{"content":"my first example post"}'
```

Удалить пост по айди

```bash
curl -X DELETE http://localhost:8080/posts/1
```



**Комментарии:**



Создать комментарий (логин, пароль перед запросом) (Указать айди автора комментария, указать айди поста)

```bash
curl -u test:123 -X POST "http://localhost:8080/comments?userId=1&postId=1" -H "Content-Type: application/json" -d '{"content":"my first comment example"}'
```

Удалить комментарий по айди

```bash
curl -X DELETE http://localhost:8080/comments/1
```

