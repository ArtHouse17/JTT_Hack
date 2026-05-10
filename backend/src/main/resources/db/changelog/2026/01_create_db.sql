-- liquibase formatted sql

-- changeset lam1ch:1-create-db
CREATE TYPE task_type_enum AS ENUM ('TEST', 'ERROR_SEARCH', 'OPEN');
CREATE TYPE task_level_enum AS ENUM ('Easy', 'Medium', 'Hard');
CREATE TYPE grade_level_enum AS ENUM ('Junior', 'Middle', 'Senior');
CREATE TYPE attempt_status_enum AS ENUM ('IN_PROGRESS', 'COMPLETED');

-- 1. Таблица пользователей
CREATE TABLE users (
                       id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                       username VARCHAR(100) UNIQUE NOT NULL,
                       pass_hash VARCHAR(255) NOT NULL,
                       firstname VARCHAR(100),
                       lastname VARCHAR(100),
                       grade_level grade_level_enum,
                       role VARCHAR(50) DEFAULT 'USER',
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Таблица заданий
CREATE TABLE tasks (
                       id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                       title VARCHAR(255) NOT NULL,
                       description TEXT,
                       task_type task_type_enum NOT NULL,
                       task_level task_level_enum,
                       grade_level grade_level_enum,
                       max_points INT NOT NULL,
                       correct_answers_count INT DEFAULT 1
);

-- 3. Варианты ответов
CREATE TABLE task_options (
                              id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                              task_id UUID REFERENCES tasks(id) ON DELETE CASCADE,
                              content TEXT NOT NULL,
                              is_correct BOOLEAN NOT NULL
);

-- 4. Попытки прохождения (с новым ENUM)
CREATE TABLE attempts (
                          id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                          user_id UUID REFERENCES users(id) ON DELETE CASCADE,
                          task_id UUID REFERENCES tasks(id) ON DELETE CASCADE,
                          status attempt_status_enum NOT NULL DEFAULT 'IN_PROGRESS',
                          earned_points INT DEFAULT 0,
                          written_text TEXT,
                          started_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          completed_at TIMESTAMP
);

-- 5. Связь попытки с выбранными вариантами ответов
CREATE TABLE attempt_answers (
                                 attempt_id UUID REFERENCES attempts(id) ON DELETE CASCADE,
                                 task_option_id UUID REFERENCES task_options(id) ON DELETE CASCADE,
                                 PRIMARY KEY (attempt_id, task_option_id)
);


-- changeset lam1ch:2-insert-analytics-test-data

-- ==========================================
-- 1. ПОЛЬЗОВАТЕЛИ
-- ==========================================
INSERT INTO users (id, username, pass_hash, firstname, lastname, grade_level, role) VALUES
                                                                                        ('11111111-1111-1111-1111-111111111111', 'junior_analyst', 'hash123', 'Алексей', 'Смирнов', 'Junior', 'USER'),
                                                                                        ('22222222-2222-2222-2222-222222222222', 'middle_analyst', 'hash123', 'Елена', 'Попова', 'Middle', 'USER'),
                                                                                        ('33333333-3333-3333-3333-333333333333', 'senior_lead', 'hash123', 'Михаил', 'Иванов', 'Senior', 'ADMIN');

-- ==========================================
-- 2. ЗАДАНИЯ
-- ==========================================

-- Задание 1: Простой тест для Junior
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES
    ('aaaa1111-1111-1111-1111-111111111111', 'Основы SQL: Агрегация', 'Какая функция используется для подсчета количества строк?', 'TEST', 'Easy', 'Junior', 10, 1);

-- ==========================================
-- 3. ВАРИАНТЫ ОТВЕТОВ
-- ==========================================

-- Варианты для Задания 1 (Тест)
INSERT INTO task_options (id, task_id, content, is_correct) VALUES
                                                                (gen_random_uuid(), 'aaaa1111-1111-1111-1111-111111111111', 'SUM()', false),
                                                                (gen_random_uuid(), 'aaaa1111-1111-1111-1111-111111111111', 'COUNT()', true),
                                                                (gen_random_uuid(), 'aaaa1111-1111-1111-1111-111111111111', 'MAX()', false);

-- ==========================================
-- 4. ПОПЫТКИ И ПРОГРЕСС ПОЛЬЗОВАТЕЛЕЙ
-- ==========================================

-- Ситуация 1: Junior успешно прошел первый тест (COMPLETED)
INSERT INTO attempts (id, user_id, task_id, status, earned_points, started_at, completed_at) VALUES
    ('dddd1111-1111-1111-1111-111111111111', '11111111-1111-1111-1111-111111111111', 'aaaa1111-1111-1111-1111-111111111111', 'COMPLETED', 10, CURRENT_TIMESTAMP - INTERVAL '1 hour', CURRENT_TIMESTAMP - INTERVAL '55 minutes');

-- Привязываем правильный ответ джуниора (ищем нужный вариант подзапросом)
INSERT INTO attempt_answers (attempt_id, task_option_id)
SELECT 'dddd1111-1111-1111-1111-111111111111', id
FROM task_options
WHERE task_id = 'aaaa1111-1111-1111-1111-111111111111' AND is_correct = true;