**Фикс**
- Добавил хеширование паролей через BCrypt
- Почистил код, комменты, перенес SQL query в локальные переменные
- Добавил в schema.sql на колонке username констранту UNIQUE, чтобы не было race condition'а

**Команды**

**Пользователи:**


Создать юзера
```bash
curl -X POST http://localhost:8080/users -H "Content-Type: application/json" -d '{"username":"test","password":"123"}'
```

Получить всех пользователей

```bash
curl -X GET http://localhost:8080/users
```

Получить пользователя по айди
```bash
curl -X GET http://localhost:8080/users?id=1
```

Удалить пользователя по айди
```bash
curl -X DELETE http://localhost:8080/users/1
```


**Посты:**




Создать пост (указать айди существующего юзера)

```bash
curl -X POST "http://localhost:8080/posts?userId=1" -H "Content-Type: application/json" -d '{"content":"my first example post"}'
```

Получить все посты

```bash
curl -X GET http://localhost:8080/posts
```

Получить пост по айди
```bash
curl -X GET http://localhost:8080/posts?id=1
```

Удалить пост по айди

```bash
curl -X DELETE http://localhost:8080/posts/1
```



**Комментарии:**

Создать комментарий (Указать айди поста, указать айди сующествующего автора комментария)

```bash
curl -X POST "http://localhost:8080/comments?postId=1&userId=1" -H "Content-Type: application/json" -d '{"content":"my first comment example"}'
```

Получить все комментарии

```bash
curl -X GET http://localhost:8080/comments
```

Получить комментарий по айди
```bash
curl -X GET http://localhost:8080/comments?id=1
```

Удалить комментарий по айди

```bash
curl -X DELETE http://localhost:8080/comments/1
```
