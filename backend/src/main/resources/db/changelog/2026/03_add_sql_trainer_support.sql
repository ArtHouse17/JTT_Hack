-- liquibase formatted sql

-- changeset jtt:3-add-sql-trainer-support runOnChange:true
CREATE SCHEMA IF NOT EXISTS sql_trainer_data;
CREATE SCHEMA IF NOT EXISTS sql_trainer;

CREATE TABLE IF NOT EXISTS sql_task_configs (
    task_id UUID PRIMARY KEY REFERENCES tasks(id) ON DELETE CASCADE,
    starter_sql TEXT NOT NULL DEFAULT '',
    expected_sql TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS sql_trainer_data.world_source (
    name VARCHAR(100) PRIMARY KEY,
    continent VARCHAR(100) NOT NULL,
    area BIGINT NOT NULL,
    population BIGINT NOT NULL,
    gdp BIGINT
);

CREATE OR REPLACE VIEW sql_trainer.world AS
SELECT name, continent, area, population, gdp
FROM sql_trainer_data.world_source;

INSERT INTO sql_trainer_data.world_source (name, continent, area, population, gdp) VALUES
    ('Afghanistan', 'Asia', 652230, 25500100, 20343000000),
    ('Albania', 'Europe', 28748, 2831741, 12960000000),
    ('Algeria', 'Africa', 2381741, 37100000, 188681000000),
    ('Andorra', 'Europe', 468, 78115, 3712000000),
    ('Angola', 'Africa', 1246700, 20609294, 100990000000),
    ('Brazil', 'South America', 8515767, 202794000, 2350000000000),
    ('China', 'Asia', 9596961, 1401430000, 14700000000000),
    ('India', 'Asia', 3287263, 1324171354, 2960000000000),
    ('Indonesia', 'Asia', 1904569, 261115456, 1010000000000),
    ('Russia', 'Europe', 17098242, 146544710, 1650000000000),
    ('United States', 'North America', 9833517, 331002651, 21400000000000)
ON CONFLICT (name) DO NOTHING;

-- changeset jtt:4-add-big-countries-task runOnChange:true
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES
    ('cccc1111-1111-1111-1111-111111111111', 'Big Countries', 'Страна считается **большой**, если:
- её площадь составляет не менее трёх миллионов квадратных километров (т.е. `3000000 км²`), или
- её население составляет не менее двадцати пяти миллионов человек (т.е. `25000000`).

Напишите запрос для поиска **name**, **population** и **area** всех больших стран.

Таблица: `World`

| Столбец     | Тип     |
|-------------|---------|
| name        | varchar |
| continent   | varchar |
| area        | int     |
| population  | int     |
| gdp         | int     |

`name` — первичный ключ таблицы.', 'OPEN', 'Easy', 'Middle', 50, 0)
ON CONFLICT (id) DO UPDATE SET description = EXCLUDED.description;

INSERT INTO sql_task_configs (task_id, starter_sql, expected_sql) VALUES
    ('cccc1111-1111-1111-1111-111111111111',
     'SELECT name, population, area
FROM world
WHERE ...;',
     'SELECT name, population, area
FROM world
WHERE area >= 3000000 OR population >= 25000000;')
ON CONFLICT (task_id) DO UPDATE SET 
    starter_sql = EXCLUDED.starter_sql,
    expected_sql = EXCLUDED.expected_sql;

INSERT INTO attempts (id, user_id, task_id, status, earned_points, written_text, started_at) VALUES
    ('dddd2222-2222-2222-2222-222222222222', '22222222-2222-2222-2222-222222222222', 'cccc1111-1111-1111-1111-111111111111', 'IN_PROGRESS', 0, NULL, CURRENT_TIMESTAMP - INTERVAL '15 minutes')
ON CONFLICT (id) DO NOTHING;

-- changeset jtt:5-add-employee-bonus-tables runOnChange:true
CREATE TABLE IF NOT EXISTS sql_trainer_data.employee_source (
    emp_id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    supervisor INT,
    salary INT NOT NULL
);

CREATE TABLE IF NOT EXISTS sql_trainer_data.bonus_source (
    emp_id INT PRIMARY KEY,
    bonus INT
);

CREATE OR REPLACE VIEW sql_trainer.employee AS
SELECT emp_id, name, supervisor, salary
FROM sql_trainer_data.employee_source;

CREATE OR REPLACE VIEW sql_trainer.bonus AS
SELECT emp_id, bonus
FROM sql_trainer_data.bonus_source;

INSERT INTO sql_trainer_data.employee_source (emp_id, name, supervisor, salary) VALUES
    (1, 'John', 3, 1000),
    (2, 'Dan', 3, 2000),
    (3, 'Brad', null, 4000),
    (4, 'Thomas', 3, 4000)
ON CONFLICT (emp_id) DO NOTHING;

INSERT INTO sql_trainer_data.bonus_source (emp_id, bonus) VALUES
    (2, 500),
    (4, 2000)
ON CONFLICT (emp_id) DO NOTHING;

-- changeset jtt:6-add-employee-bonus-task runOnChange:true
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES
    ('cccc2222-2222-2222-2222-222222222222', 'Employee Bonus', 'Таблица `Employee` содержит информацию о сотрудниках, а таблица `Bonus` — информацию о их бонусах.

Некоторые сотрудники могут не иметь бонуса (запись отсутствует в таблице `Bonus`).

Напишите запрос для поиска **name** и **bonus** всех сотрудников, у которых бонус составляет менее 1000 или отсутствует.

Верните таблицу с колонками `name` и `bonus` (может быть null).

Таблица: `Employee`

| Столбец     | Тип     |
|-------------|---------|
| emp_id      | int     |
| name        | varchar |
| supervisor  | int     |
| salary      | int     |

`emp_id` — первичный ключ таблицы.

Таблица: `Bonus`

| Столбец | Тип  |
|---------|------|
| emp_id  | int  |
| bonus   | int  |

`emp_id` — первичный ключ таблицы.', 'OPEN', 'Easy', 'Middle', 50, 0)
ON CONFLICT (id) DO UPDATE SET description = EXCLUDED.description;

INSERT INTO sql_task_configs (task_id, starter_sql, expected_sql) VALUES
    ('cccc2222-2222-2222-2222-222222222222',
     'SELECT e.name, b.bonus
FROM employee e
...;',
     'SELECT e.name, b.bonus
FROM employee e
LEFT JOIN bonus b ON e.emp_id = b.emp_id
WHERE b.bonus < 1000 OR b.bonus IS NULL;')
ON CONFLICT (task_id) DO UPDATE SET 
    starter_sql = EXCLUDED.starter_sql,
    expected_sql = EXCLUDED.expected_sql;

-- changeset jtt:7-add-users-contest-tables runOnChange:true
CREATE TABLE IF NOT EXISTS sql_trainer_data.users_source (
    user_id INT PRIMARY KEY,
    user_name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS sql_trainer_data.register_source (
    contest_id INT NOT NULL,
    user_id INT NOT NULL,
    PRIMARY KEY (contest_id, user_id)
);

CREATE OR REPLACE VIEW sql_trainer.users AS
SELECT user_id, user_name
FROM sql_trainer_data.users_source;

CREATE OR REPLACE VIEW sql_trainer.register AS
SELECT contest_id, user_id
FROM sql_trainer_data.register_source;

INSERT INTO sql_trainer_data.users_source (user_id, user_name) VALUES
    (1, 'Alice'),
    (2, 'Bob'),
    (3, 'Alex'),
    (4, 'Donald'),
    (5, 'Lee'),
    (6, 'Jonathan'),
    (7, 'Elvis'),
    (8, 'Ann'),
    (9, 'Zelda')
ON CONFLICT (user_id) DO NOTHING;

INSERT INTO sql_trainer_data.register_source (contest_id, user_id) VALUES
    (1, 1), (1, 2), (1, 3), (1, 4), (1, 5),
    (2, 1), (2, 2), (2, 3), (2, 4), (2, 5), (2, 6), (2, 7),
    (3, 1), (3, 2), (3, 3), (3, 4), (3, 5), (3, 6), (3, 7), (3, 8), (3, 9)
ON CONFLICT (contest_id, user_id) DO NOTHING;

-- changeset jtt:8-add-users-contest-task runOnChange:true
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES
    ('cccc3333-3333-3333-3333-333333333333', 'Percentage of Users Attended a Contest', 'Таблица `Users` содержит информацию о пользователях, а таблица `Register` — информацию о том, какие пользователи зарегистрировались на какие конкурсы.

Напишите запрос для вычисления процента пользователей, зарегистрированных на каждый конкурс.

Результат должен быть округлён до двух знаков после запятой.

Верните таблицу с колонками `contest_id` и `percentage`, отсортированную по убыванию `percentage`, а затем по возрастанию `contest_id`.

Таблица: `Users`

| Столбец   | Тип     |
|-----------|---------|
| user_id   | int     |
| user_name | varchar |

`user_id` — первичный ключ таблицы.

Таблица: `Register`

| Столбец    | Тип  |
|------------|------|
| contest_id | int  |
| user_id    | int  |

`(contest_id, user_id)` — составной первичный ключ таблицы.', 'OPEN', 'Easy', 'Middle', 50, 0)
ON CONFLICT (id) DO UPDATE SET description = EXCLUDED.description;

INSERT INTO sql_task_configs (task_id, starter_sql, expected_sql) VALUES
    ('cccc3333-3333-3333-3333-333333333333',
     'SELECT contest_id, ...
FROM register
...;',
     'SELECT contest_id,
       ROUND(COUNT(user_id) * 100.0 / (SELECT COUNT(*) FROM users), 2) AS percentage
FROM register
GROUP BY contest_id
ORDER BY percentage DESC, contest_id ASC;')
ON CONFLICT (task_id) DO UPDATE SET
    starter_sql = EXCLUDED.starter_sql,
    expected_sql = EXCLUDED.expected_sql;
