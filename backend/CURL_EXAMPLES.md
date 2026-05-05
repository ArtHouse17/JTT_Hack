# Примеры тестирования API с cookies

## Регистрация новогопользователя

```bash
curl http://localhost:7070/auth/signup \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "password": "testpassword123",
    "firstname": "Тест",
    "lastname": "Пользователь"
  }' \
  -v
```

Успешный ответ:
```
HTTP/1.1 201
Set-Cookie: session_id=<uuid>; Max-Age=86400
{
  "message": "Успешная регистрация"
}
```

## Вход в систему

```bash
curl -X POST http://localhost:7070/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "password": "testpassword123"
  }' \
  -c cookies.txt \
  -v
```

Успешный ответ:
```
HTTP/1.1 200
Set-Cookie: session_id=<uuid>; Max-Age=86400
{
  "message": "Успешный вход"
}
```

## Сохранение cookie и использование для следующих запросов

```bash
curl -X POST http://localhost:7070/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "password": "testpassword123"
  }' \
  -c cookies.txt
```

## Доступ к защищенному ресурсу с cookie

```bash
curl -X GET http://localhost:7070/users/me \
  -b cookies.txt \
  -v
```

Успешный ответ: 200 OK
Без cookie: 401 Unauthorized

## Выход из системы

```bash
curl -X POST http://localhost:7070/auth/logout \
  -b cookies.txt \
  -v
```

Успешный ответ:
```
HTTP/1.1 200
Set-Cookie: session_id=; Max-Age=0
{
  "message": "Успешный выход"
}
```

## Попытка доступа к защищенному ресурсу без авторизации

```bash
curl -X GET http://localhost:7070/users/me \
  -v
```

Ошибка:
```
HTTP/1.1 401
{
  "message": "Требуется авторизация"
}
```