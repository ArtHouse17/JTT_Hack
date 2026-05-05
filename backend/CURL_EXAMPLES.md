# Примеры тестирования API с cookies

## Регистрация новогопользователя

```bash
curl -X POST http://localhost:8080/auth/signup \
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
curl -X POST http://localhost:8080/auth/login \
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
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "password": "testpassword123"
  }' \
  -c cookies.txt
```

## Доступ к защищенному ресурсу с cookie

```bash
curl -X GET http://localhost:8080/api/tasks \
  -b cookies.txt \
  -v
```

Успешный ответ: 200 OK
Без cookie: 401 Unauthorized

## Выход из системы

```bash
curl -X POST http://localhost:8080/auth/logout \
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
curl -X GET http://localhost:8080/api/tasks \
  -v
```

Ошибка:
```
HTTP/1.1 401
{
  "message": "Требуется авторизация"
}
```

## Одна команда для полного цикла

```bash
# 1. Регистрация
curl -X POST http://localhost:8080/auth/signup \
  -H "Content-Type: application/json" \
  -d '{
    "username": "fullcycleuser",
    "password": "password123",
    "firstname": "Полный",
    "lastname": "Цикл"
  }' \
  -c cookies.txt

# 2. Доступ к защищенному ресурсу
curl -X GET http://localhost:8080/api/tasks \
  -b cookies.txt

# 3. Выход
curl -X POST http://localhost:8080/auth/logout \
  -b cookies.txt
```

## Тестирование с Postman

1. Откройте Postman
2. Создайте POST запрос на `http://localhost:8080/auth/signup`
3. В вкладке "Body" выберите "raw" и "JSON"
4. Введите JSON:
```json
{
  "username": "postmanuser",
  "password": "password123",
  "firstname": "Постман",
  "lastname": "Юзер"
}
```
5. Нажмите "Send"
6. В ответе должна быть установлена cookie `session_id`
7. Postman автоматически сохранит cookie
8. Все последующие запросы будут использовать сохраненную cookie

## Ошибки и их значения

### Ошибка при регистрации: пользователь уже существует
```json
{
  "message": "Пользователь с таким именем уже существует"
}
```
HTTP Status: 400

### Ошибка при входе: неверные учетные данные
```json
{
  "message": "Неверное имя пользователя или пароль"
}
```
HTTP Status: 401

### Ошибка: требуется авторизация
```json
{
  "message": "Требуется авторизация"
}
```
HTTP Status: 401

