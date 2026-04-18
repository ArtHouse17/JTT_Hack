-- liquibase formatted sql

-- changeset demidovan:1-update-attempr-answ

alter table attempt_answers drop constraint attempt_answers_pkey;
delete from attempt_answers;
alter table attempt_answers add column id uuid PRIMARY KEY;
-- Привязываем правильный ответ джуниора (ищем нужный вариант подзапросом)
INSERT INTO attempt_answers (id, attempt_id, task_option_id)
SELECT 'cccc1111-1111-1111-1111-111111111111', 'dddd1111-1111-1111-1111-111111111111', id
FROM task_options
WHERE task_id = 'aaaa1111-1111-1111-1111-111111111111' AND is_correct = true;


