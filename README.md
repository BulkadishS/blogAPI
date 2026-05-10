**Обновление**
- Добавил Security, подстроил обработку ошибок с ним, и таблицу ролей, связанную с юзерами в том числе
- Попробовал транзакции, с JDBC Template сделал с чистым sql, пока сыро, но на примере есть. Ничего не поменялось толком, в коде лишь видно
- Сделал data.sql, которая создает админа при старте апишки. Айди админа всегда 5. С ним можешь уже проверить привелегии его

***Аутентификация/Авторизация***
- Настроил привелегии
- Неавторизованные юзеры - только просмотр (GET)
- Юзер - просмотр + создание комментариев
- Админ - полный доступ(создание постов, комментариев, удаление)


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
curl -u "root:123123" -X POST "http://localhost:8080/posts?userId=1" -H "Content-Type: application/json" -d '{"content":"my first example post"}'
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
curl -u "root:123123" -X DELETE http://localhost:8080/posts/1
```



**Комментарии:**

Создать комментарий (Указать айди поста, указать айди существующего автора комментария)

```bash
curl -u "test:123" -X POST "http://localhost:8080/comments?postId=1&userId=1" -H "Content-Type: application/json" -d '{"content":"my first comment example"}'
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
curl -u "root:123123" -X DELETE http://localhost:8080/comments/1
```


