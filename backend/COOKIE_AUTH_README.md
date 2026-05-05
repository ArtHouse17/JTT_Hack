# Система авторизации через Cookies

## Описание

Приложение использует систему авторизации на основе cookies для управления сеансами пользователей. Все классы, отвечающие за авторизацию, находятся в пакете `com.jtt.javatachteam_hakaton.security`.

## Архитектура

### Основные компоненты

#### 1. CookieSessionManager
Менеджер для управления сеансами через cookies. Основные методы:
- `createSessionCookie(UUID userId)` - создает новый сеанс и возвращает ID сеанса
- `getUserIdFromSessionCookie(String sessionId)` - получает ID пользователя из ID сеанса
- `invalidateSession(String sessionId)` - инвалидирует сеанс пользователя
- `getSessionCookieName()` - возвращает имя cookie переменной: `session_id`
- `getCookieMaxAge()` - возвращает время жизни cookie в секундах: 24 часа

#### 2. SessionStore
Хранилище активных сеансов в памяти. Использует `ConcurrentHashMap` для потокобезопасности.
- Автоматически удаляет истекшие сеансы (через 24 часа)
- Методы: `createSession()`, `getUserIdFromSession()`, `removeSession()`, `cleanExpiredSessions()`

#### 3. AuthMiddleware
Middleware для проверки авторизации. Проверяет наличие валидного сеанса перед обра��откой защищенных маршрутов.
- Публичные пути: `/auth/login`, `/auth/signup`, `/health`
- Возвращает `401 Unauthorized` если сеанс истек или отсутствует

## Использование

### 1. Регистрация пользователя

```bash
POST /auth/signup
Content-Type: application/json

{
  "username": "ivanov",
  "password": "securePassword123",
  "firstname": "Иван",
  "lastname": "Иванов"
}

Ответ:
{
  "message": "Успешная регистрация"
}

Cookies: 
Set-Cookie: session_id=<uuid>; Max-Age=86400; Path=/
```

### 2. Вход в систему

```bash
POST /auth/login
Content-Type: application/json

{
  "username": "ivanov",
  "password": "securePassword123"
}

Ответ:
{
  "message": "Успешный вход"
}

Cookies:
Set-Cookie: session_id=<uuid>; Max-Age=86400; Path=/
```

### 3. Выход из системы

```bash
POST /auth/logout
Cookie: session_id=<uuid>

Ответ:
{
  "message": "Успешный выход"
}

Cookies:
Set-Cookie: session_id=; Max-Age=0; Path=/
```

### 4. Доступ к защищенным маршрутам

Все остальные маршруты (кроме `/auth/login`, `/auth/signup`, `/health`) требуют авторизации.

```bash
GET /api/tasks
Cookie: session_id=<uuid>

Успешный ответ: 200 OK
Без cookie или с истекшим сеансом: 401 Unauthorized
```

## Изменения в существующих классах

### AuthService
Добавлены методы:
- `loginWithCookie(String username, String password)` - в��звращает UUID пользователя
- `signupWithCookie(String username, String password, String firstname, String lastname)` - возвращает UUID пользователя

Старые методы `login()` и `signup()` сохранены для обратной совместимости.

### AuthHandler
Полностью переработан для использования cookies вместо JWT токенов:
- В ответе `login` и `signup` устанавливается HTTP cookie с ID сеанса
- Метод `logout` инвалидирует сеанс и удаляет cookie

### Main.java
Добавлена конфигурация для работы с cookies:
- Установлена конфигурация типа контента по умолчанию: `application/json`

## Безопасность

1. **Время жизни сеанса**: 24 часа
2. **Хранилище**: Сеансы хранятся в памяти на сервере
3. **Шифрование пароля**: Используется BCrypt для хеширования паролей
4. **Cookie флаги**: HttpOnly, Secure (при использовании HTTPS)

## Развертывание

Приложение собирается в файл `target/app.jar`:

```bash
./mvnw clean package
java -jar target/app.jar
```

## Для будущих улучшений

1. Сохранение сеансов в БД для масштабируемости
2. Добавление поддержки Redis для хранилища сеансов
3. Реализация встроенного middleware в Javalin для автоматической проверки
4. Добавление поддержки CSRF токенов
5. Р��ализация механизма обновления сеанса (refresh)

