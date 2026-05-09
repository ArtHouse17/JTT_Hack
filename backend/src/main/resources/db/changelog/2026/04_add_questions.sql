-- liquibase formatted sql

-- changeset nikolaj:add-questions

-- 1. Теория вероятностей · Тест 1
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    'b07e222e-ccc0-5cc4-8e49-295a90d56759',
    'Теория вероятностей · Тест 1',
    '1. Какое из утверждений относительно генеральной и выборочной совокупностей является верным?',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '097c3016-c5ef-5043-b1f1-e2ef011f44d2',
    'b07e222e-ccc0-5cc4-8e49-295a90d56759',
    'выборочная совокупность – часть генеральной',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'b746114e-9389-5cb8-8c7c-0c5e5de9f03d',
    'b07e222e-ccc0-5cc4-8e49-295a90d56759',
    'генеральная совокупность – часть выборочной',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'ee87a8fb-3bd9-59cf-9cfa-7c734375db8a',
    'b07e222e-ccc0-5cc4-8e49-295a90d56759',
    'выборочная и генеральная совокупности равны по численности',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'a82ca489-e439-5619-9f3a-09fad6a95000',
    'b07e222e-ccc0-5cc4-8e49-295a90d56759',
    'правильный ответ отсутствует',
    false
);

-- 2. Теория вероятностей · Тест 2
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '92d824d9-c98e-5948-a3c2-c510e5b79c7d',
    'Теория вероятностей · Тест 2',
    '2. Сумма частот признака равна:',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '73f5fe99-5a73-591e-893c-b86f3ec82e61',
    '92d824d9-c98e-5948-a3c2-c510e5b79c7d',
    'объему выборки n',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'c783d789-82a0-50c2-8543-3e761bf6d6ef',
    '92d824d9-c98e-5948-a3c2-c510e5b79c7d',
    'среднему арифметическому значений признака',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '7dabc63a-6d27-5c40-89f7-1e355ce924cb',
    '92d824d9-c98e-5948-a3c2-c510e5b79c7d',
    'нулю',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '52f22190-a446-5472-84f4-14d2955d6252',
    '92d824d9-c98e-5948-a3c2-c510e5b79c7d',
    'единице',
    false
);

-- 3. Теория вероятностей · Тест 3
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '68622fcd-cc01-5e49-9026-2e9c939b6f0d',
    'Теория вероятностей · Тест 3',
    '3. Ломаная, отрезки которой соединяют точки с координатами – частота, – это:',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'f89c49e9-b39f-5401-ae97-055b3c34f106',
    '68622fcd-cc01-5e49-9026-2e9c939b6f0d',
    'гистограмма',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '7ccb075e-b34d-58a0-90e8-546c946d285d',
    '68622fcd-cc01-5e49-9026-2e9c939b6f0d',
    'эмпирическая функция распределения',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '74a7ddc3-e0e4-5f57-86c7-fc9fa3fdb66b',
    '68622fcd-cc01-5e49-9026-2e9c939b6f0d',
    'полигон',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'b970396b-7e52-5ac5-8248-815580712ec8',
    '68622fcd-cc01-5e49-9026-2e9c939b6f0d',
    'кумулята',
    false
);

-- 4. Теория вероятностей · Тест 4
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '5784ec91-3837-57a7-b6a2-0f2ba6bd1860',
    'Теория вероятностей · Тест 4',
    '4. Какие из следующих утверждений являются верными?',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '01135d04-6249-592b-aba0-cf36bdfccf7b',
    '5784ec91-3837-57a7-b6a2-0f2ba6bd1860',
    'выборочное среднее является интервальной оценкой математического ожидания M(X), а выборочная дисперсия – интервальной оценкой дисперсии D(X)',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '6e745dff-4993-5bbb-b4b7-b21e644c94e6',
    '5784ec91-3837-57a7-b6a2-0f2ba6bd1860',
    'выборочное среднее является точечной оценкой математического ожидания M(X), а выборочная дисперсия - интервальной оценкой дисперсии D(X)',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '143ed6d3-4b9f-5083-8366-b9276b04a3c6',
    '5784ec91-3837-57a7-b6a2-0f2ba6bd1860',
    'выборочное среднее является точечной оценкой математического ожидания M(X), а выборочная дисперсия - точечной оценкой дисперсии D(X)',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '83ddf0ef-5c19-5c2b-85ca-40715ebc251b',
    '5784ec91-3837-57a7-b6a2-0f2ba6bd1860',
    'выборочное среднее является интервальной оценкой математического ожидания M(X), а выборочная дисперсия – точечной оценкой дисперсии D(X)',
    false
);

-- 5. Теория вероятностей · Тест 5
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '0f1a40ad-5ceb-5fcd-80ac-2ecf6caab6ee',
    'Теория вероятностей · Тест 5',
    '5. Уточненная выборочная дисперсия случайной величины обладает следующими свойствами:',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'e9e87028-22bf-54b6-86cc-1c747c254ea6',
    '0f1a40ad-5ceb-5fcd-80ac-2ecf6caab6ee',
    'является смещенной оценкой дисперсии случайной величины X',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'c6f87e50-f23c-5c74-96b5-234602e7247d',
    '0f1a40ad-5ceb-5fcd-80ac-2ecf6caab6ee',
    'является несмещенной оценкой дисперсии случайной величины X',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '2f8468cc-cc6f-5df6-ac86-991ad6938871',
    '0f1a40ad-5ceb-5fcd-80ac-2ecf6caab6ee',
    'является смещенной оценкой среднеквадратического отклонения случайной величины X',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'f3a9e57b-ebc7-512a-b5f4-3154369aee71',
    '0f1a40ad-5ceb-5fcd-80ac-2ecf6caab6ee',
    'является несмещенной оценкой среднеквадратического отклонения случайной величины X',
    false
);

-- 6. Теория вероятностей · Тест 6
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '53bff4cc-d748-5926-8c48-5c712ae41614',
    'Теория вероятностей · Тест 6',
    '6. По выборке объема получена выборочная диcперсия равна',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'c2ca1544-b847-5a3e-8047-87a6c8afe5d8',
    '53bff4cc-d748-5926-8c48-5c712ae41614',
    '100',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '5e94ff6b-4e38-5cdd-94f3-7023f8ada4f9',
    '53bff4cc-d748-5926-8c48-5c712ae41614',
    '80',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '344117dd-d16d-5a1e-830f-037001b84272',
    '53bff4cc-d748-5926-8c48-5c712ae41614',
    '90',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'a60c9b74-cefd-5bc4-9381-2d449d2d4879',
    '53bff4cc-d748-5926-8c48-5c712ae41614',
    '81',
    false
);

-- 7. Теория вероятностей · Тест 7
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    'c8ef603b-deb9-5bd3-af67-9958906531c5',
    'Теория вероятностей · Тест 7',
    '7. Статистической гипотезой называют:',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'e2566455-289f-5b9c-9da5-1b994ed2c057',
    'c8ef603b-deb9-5bd3-af67-9958906531c5',
    'предположение относительно статистического критерия',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '1feef338-34e0-5552-b0a9-5bb6bfec88f4',
    'c8ef603b-deb9-5bd3-af67-9958906531c5',
    'предположение относительно параметров или вида закона распределения генеральной совокупности',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '3b452dfb-bde6-5cb4-ba4a-bd71389c7ed1',
    'c8ef603b-deb9-5bd3-af67-9958906531c5',
    'предположение относительно объема генеральной совокупности',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '5399b9e0-eea1-5b73-8812-d7273528bd88',
    'c8ef603b-deb9-5bd3-af67-9958906531c5',
    'предположение относительно объема выборочной совокупности',
    false
);

-- 8. Теория вероятностей · Тест 8
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '51dbca67-eaaa-5aa2-9996-cee2cf2ab152',
    'Теория вероятностей · Тест 8',
    '8. При проверке статистической гипотезы, ошибка первого рода - это:',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'dd143a2b-dfa1-59de-b599-5b0597ecc15d',
    '51dbca67-eaaa-5aa2-9996-cee2cf2ab152',
    'принятие нулевой гипотезы, которая в действительности является неверной',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '43885f03-9efb-52ad-8885-eacd7a2898a3',
    '51dbca67-eaaa-5aa2-9996-cee2cf2ab152',
    'отклонение альтернативной гипотезы, которая в действительности является верной',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '94829594-6cdc-5f6b-88f3-b2c2c70a490a',
    '51dbca67-eaaa-5aa2-9996-cee2cf2ab152',
    'принятие альтернативной гипотезы, которая в действительности является неверной',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '7d31d9aa-efdf-51e0-923f-a4797fe49762',
    '51dbca67-eaaa-5aa2-9996-cee2cf2ab152',
    'отклонение нулевой гипотезы, которая в действительности является верной',
    true
);

-- 9. Теория вероятностей · Тест 9
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '6d91d78b-ce90-58e3-b466-c0610e904b2a',
    'Теория вероятностей · Тест 9',
    '9. Мощность критерия – это:',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '57e20196-e547-5c34-a697-d3c64767ed8f',
    '6d91d78b-ce90-58e3-b466-c0610e904b2a',
    'вероятность не допустить ошибку второго рода',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '821d7fab-d886-5545-a11f-ed17e304f0eb',
    '6d91d78b-ce90-58e3-b466-c0610e904b2a',
    'вероятность допустить ошибку второго рода',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '01fde061-c7e6-5d9d-b1c4-3ab18d2b34ca',
    '6d91d78b-ce90-58e3-b466-c0610e904b2a',
    'вероятность отвергнуть нулевую гипотезу, когда она неверна',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '31d6c908-ab57-5f31-a40b-88f62203d120',
    '6d91d78b-ce90-58e3-b466-c0610e904b2a',
    'вероятность отвергнуть нулевую гипотезу, когда она верна',
    false
);

-- 10. Теория вероятностей · Тест 10
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '470c0cc8-5120-5cf5-8acf-2fc41b3a5b01',
    'Теория вероятностей · Тест 10',
    '10. Какие из названных распределений используются при проверке гипотезы о числовом значении математического ожидания при неизвестной дисперсии?',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '00322578-facc-5606-8067-9a7b75b69a6e',
    '470c0cc8-5120-5cf5-8acf-2fc41b3a5b01',
    'распределение Стьюдента',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '52f1303a-2425-51af-a81d-0ef1c450e796',
    '470c0cc8-5120-5cf5-8acf-2fc41b3a5b01',
    'распределение Фишера',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '897a8e3c-eb7a-5ec4-8638-f92a5d27193b',
    '470c0cc8-5120-5cf5-8acf-2fc41b3a5b01',
    'нормальное распределение',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '7afe8549-8c5a-5dec-aeb2-7d02556bae75',
    '470c0cc8-5120-5cf5-8acf-2fc41b3a5b01',
    'распределение хи-квадрат',
    false
);

-- 11. Теория вероятностей · Тест 11
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    'ac245a2d-8d2f-5580-add2-83c1ca63d062',
    'Теория вероятностей · Тест 11',
    '11. Что представляет собой критическая область?',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '65eaace3-be70-5766-9904-368dcf98c92d',
    'ac245a2d-8d2f-5580-add2-83c1ca63d062',
    'все возможные значения критерия, при которых принимается нулевая гипотеза',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'f0bfe429-0e43-500b-b7b8-79b8ff2cb219',
    'ac245a2d-8d2f-5580-add2-83c1ca63d062',
    'все возможные значения критерия, при которых не может быть принята ни нулевая, ни альтернативная гипотеза',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '052d7655-8a94-5380-a039-10e325cc880b',
    'ac245a2d-8d2f-5580-add2-83c1ca63d062',
    'все возможные значения критерия, при которых есть основание принять альтернативную гипотезу',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '030964ce-a44f-5a44-bdda-de01593694a2',
    'ac245a2d-8d2f-5580-add2-83c1ca63d062',
    'нет правильного ответа',
    false
);

-- 12. Теория вероятностей · Тест 12
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '2316d556-b28b-51b1-80f3-c3af3b1579db',
    'Теория вероятностей · Тест 12',
    '12. Для чего при проверке гипотезы о равенстве средних двух совокупностей должна быть проведена вспомогательная процедура?',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '8d37adc4-afc7-512a-ae45-dd53d5ff5739',
    '2316d556-b28b-51b1-80f3-c3af3b1579db',
    'чтобы установить, равны ли объемы выборок',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'eb5820d6-19f1-5ec7-b89a-9f57485e9e1c',
    '2316d556-b28b-51b1-80f3-c3af3b1579db',
    'чтобы установить, равны ли дисперсии в генеральных совокупностях',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '01b30233-be5f-5c1b-8ab9-3c38a5aca7a0',
    '2316d556-b28b-51b1-80f3-c3af3b1579db',
    'чтобы установить, равны ли объемы выборок и равны ли дисперсии в генеральных совокупностях',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '23645fbd-e515-59e0-b62c-3b93ce409734',
    '2316d556-b28b-51b1-80f3-c3af3b1579db',
    'нет правильного ответа',
    false
);

-- 13. SQL · Тест 1
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    'a08d1005-3281-5626-973b-eebaf858d4c0',
    'SQL · Тест 1',
    '1. Необходимо найти пользователей, которые работают на одном предприятии: все они регистрировались в сервисе с почтовых ящиков на корпоративном домене. Какой оператор в SQL-запросе поможет это сделать?',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '169bfe45-72de-5cce-9182-518db207f7dc',
    'a08d1005-3281-5626-973b-eebaf858d4c0',
    '=',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'dbac0094-eabc-520a-bc25-517d700fbd98',
    'a08d1005-3281-5626-973b-eebaf858d4c0',
    'LIKE',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '7b26e708-17bb-56bd-b118-e2ba7e9e3fd1',
    'a08d1005-3281-5626-973b-eebaf858d4c0',
    'IN',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '0b5779cb-e32f-5422-a3ec-48ba450c43a3',
    'a08d1005-3281-5626-973b-eebaf858d4c0',
    'BETWEEN',
    false
);

-- 14. SQL · Тест 2
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    'f58c4649-b2b9-5c4d-a53e-b4226c06ca9c',
    'SQL · Тест 2',
    '2. Какие операторы в SQL-запросе нужно использовать, чтобы вычислить среднее арифметическое по набору строк?',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'aaa21208-077c-54ab-a418-a065daa808bf',
    'f58c4649-b2b9-5c4d-a53e-b4226c06ca9c',
    'WHERE',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '2ae7fc1e-8b78-5e1c-b711-e683c60c986b',
    'f58c4649-b2b9-5c4d-a53e-b4226c06ca9c',
    'HAVING',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '6e64941c-9757-5da5-9fc6-0ae6d0761d16',
    'f58c4649-b2b9-5c4d-a53e-b4226c06ca9c',
    'GROUP BY, HAVING',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '73ec0f0d-e2e2-5259-8f8c-490a7d1d3465',
    'f58c4649-b2b9-5c4d-a53e-b4226c06ca9c',
    'GROUP BY',
    true
);

-- 15. SQL · Тест 3
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '861ff399-3af4-57a2-a843-3fcf59b34d85',
    'SQL · Тест 3',
    '3. Какой SQL-запрос выведет все данные о покупках пользователя SuperUser в порядке убывания стоимости товаров:',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '44f70a47-e0fe-5c29-b123-ff7eaa18565e',
    '861ff399-3af4-57a2-a843-3fcf59b34d85',
    'SELECT * FROM Payments WHERE user_name = ‘SuperUser’ ORDER BY unit_price DESC;',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '7d0e9a5b-6c7e-56fd-87a8-bf8386a19ccb',
    '861ff399-3af4-57a2-a843-3fcf59b34d85',
    'SELECT * FROM Payments WHERE user_name = ‘SuperUser’;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '6cbcce65-c769-533c-8537-5a2898ba41dc',
    '861ff399-3af4-57a2-a843-3fcf59b34d85',
    'SELECT unit_name FROM Payments;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'b8bb90cc-27f0-56ce-a315-38d04ac8ce85',
    '861ff399-3af4-57a2-a843-3fcf59b34d85',
    'SELECT unit_name FROM Payments WHERE user_name = ‘SuperUser’;',
    false
);

-- 16. SQL · Тест 4
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    'a0b5f247-08bb-5ad0-ad57-cb4e0057ab3c',
    'SQL · Тест 4',
    '4. Какое утверждение верно при проектировании структуры реляционной базы данных?',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '7497736b-eb0b-5a84-82fd-e489949439af',
    'a0b5f247-08bb-5ad0-ad57-cb4e0057ab3c',
    'Первичный ключ состоит из нескольких внешних ключей',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'f5229385-19b6-5ea7-b9fb-9df3c5b7c1e7',
    'a0b5f247-08bb-5ad0-ad57-cb4e0057ab3c',
    'Для идентификации записи в зависимых сущностях используются внешние ключи',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'fffda855-dbc0-5281-ac2c-6904ec6eb870',
    'a0b5f247-08bb-5ad0-ad57-cb4e0057ab3c',
    'Для идентификации записи в зависимых сущностях используются первичные ключи',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'aadb1536-1c40-5a12-8dfd-2abd8490fb0a',
    'a0b5f247-08bb-5ad0-ad57-cb4e0057ab3c',
    'Составной первичный ключ называется внешним ключом',
    false
);

-- 17. SQL · Тест 5
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    'fcdacaee-715d-583c-ad18-11470f18240c',
    'SQL · Тест 5',
    '5. На концептуальном уровне, чтобы показать значимые сущности предметной области, следует использовать',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '586fce9a-10ef-5141-977b-7e6ac6be5ad2',
    'fcdacaee-715d-583c-ad18-11470f18240c',
    'можно строить как ER-модель, так и диаграмму классов UML',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'c76d23e2-bf8d-5135-9bed-92d15630d509',
    'fcdacaee-715d-583c-ad18-11470f18240c',
    'используется только UML-диаграмма классов',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'ab37f5b5-4ad4-58e2-b385-d53447043c57',
    'fcdacaee-715d-583c-ad18-11470f18240c',
    'используются специальные концептуальные модели',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'c0aa38f2-0333-5c54-8f6f-47de1a6f39e9',
    'fcdacaee-715d-583c-ad18-11470f18240c',
    'используется только ERD-модель',
    false
);

-- 18. SQL · Тест 6
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '6cce022c-88d7-58c8-b3d5-8e8e38d80280',
    'SQL · Тест 6',
    '6. Какой SQL-запрос выведет общую сумму покупок для каждого корпоративного клиента, т.е. тех пользователей, кто работает на одном предприятии, где общая сумма покупки больше 25000 рублей:',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '7ff34287-a02f-508d-962a-50df8e63500f',
    '6cce022c-88d7-58c8-b3d5-8e8e38d80280',
    'SELECT company_member, SUM(unit_price * amount) AS sum FROM Payments WHERE sum > 25000 GROUP BY company_member;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '9bebd0b8-f188-5913-8c7f-7af6a2070465',
    '6cce022c-88d7-58c8-b3d5-8e8e38d80280',
    'SELECT company_member, SUM(unit_price * amount) AS sum FROM Payments WHERE sum > 25000;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'f5da47cc-bf56-5278-bf17-799d3532da58',
    '6cce022c-88d7-58c8-b3d5-8e8e38d80280',
    'SELECT company_member, SUM(unit_price * amount) AS sum AND sum > 25000;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'f03b3f04-1753-5791-9852-0e2c8f6dde68',
    '6cce022c-88d7-58c8-b3d5-8e8e38d80280',
    'SELECT company_member, SUM(unit_price * amount) AS sum FROM Payments GROUP BY company_member HAVING sum > 25000;',
    true
);

-- 19. SQL · Тест 7
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    'f9498f31-4679-51b6-9e16-f421b47cffb3',
    'SQL · Тест 7',
    '7. Отметьте верные утверждения об операторе UNION:',
    'TEST',
    'Hard',
    'Senior',
    10,
    4
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '8a5172cd-d484-56bd-9c8e-1618b65896f5',
    'f9498f31-4679-51b6-9e16-f421b47cffb3',
    'оператор объединяет записи из разных таблиц по равным значениям, заданным в ключе соединения',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'ce937fc3-6a8d-5e43-b54e-1f220fc3b185',
    'f9498f31-4679-51b6-9e16-f421b47cffb3',
    'оператор работает для таблиц со схожей структурой',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'ae4d6d01-5cc2-59ba-bd0f-c988303b8bcc',
    'f9498f31-4679-51b6-9e16-f421b47cffb3',
    'оператор нужен для выполнения подзапросов',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '3d592f79-f3cf-5130-8cf3-fd490d30da2f',
    'f9498f31-4679-51b6-9e16-f421b47cffb3',
    'в результате его выполнения таблицы должны иметь одинаковое число столбцов с одним и тем же типом данных и в той же самой последовательности',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'f1a3af23-b0d9-5b02-b477-9762c039f505',
    'f9498f31-4679-51b6-9e16-f421b47cffb3',
    'оператор объединяет несколько запросов SELECT, возвращая записи только первого запроса, которые имеют совпадения в последующих',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '1db639e4-b3cc-59b9-aab6-966ff6ec6dbc',
    'f9498f31-4679-51b6-9e16-f421b47cffb3',
    'оператор работает только со связанными таблицами',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '60d1b11c-0ad2-57bf-b584-6f0f69070a1b',
    'f9498f31-4679-51b6-9e16-f421b47cffb3',
    'оператор объединяет результаты выполнения запросов',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '8d308041-aa97-51d1-955c-d65ac90fd236',
    'f9498f31-4679-51b6-9e16-f421b47cffb3',
    'по умолчанию результат выводится без повторяющихся строк, чтобы показать все строки (с повторами) следует добавить к оператору необязательный параметр ALL',
    true
);

-- 20. SQL · Тест 8
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    'c373188d-1726-54e7-a5f9-d6c4c6544edb',
    'SQL · Тест 8',
    '8. Отметьте верные утверждения о типах данных для следующих полей:',
    'TEST',
    'Hard',
    'Middle',
    10,
    4
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '1a65e202-815e-5fe9-bb65-9c1ab765d0be',
    'c373188d-1726-54e7-a5f9-d6c4c6544edb',
    'Email клиента — строковый тип данных',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '66386773-38ec-57e1-aeea-91d5a1a9b235',
    'c373188d-1726-54e7-a5f9-d6c4c6544edb',
    'ФИО клиента — тип данных TEXT',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '9c3a39f8-9ec7-50ab-86c6-95c8dd8955b5',
    'c373188d-1726-54e7-a5f9-d6c4c6544edb',
    'Признак VIP-клиента — логический тип данных',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'a6c113ae-118d-5c1c-92ed-904f6d04820c',
    'c373188d-1726-54e7-a5f9-d6c4c6544edb',
    'ФИО клиента — тип данных CHAR',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '2511992c-353c-5056-a843-c2bb8523d32c',
    'c373188d-1726-54e7-a5f9-d6c4c6544edb',
    'Общая сумма покупок пользователя — числовой тип данных',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '0e4b4650-54dc-5c44-8d6f-40346eeb9ad7',
    'c373188d-1726-54e7-a5f9-d6c4c6544edb',
    'Дата регистрации — числовой тип данных',
    false
);

-- 21. SQL · Тест 9
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '7c090048-602d-5f32-93da-d64e07d36b29',
    'SQL · Тест 9',
    '9. Отметьте верные утверждения относительно создания и удаления таблиц в базе данных с помощью DML-операторов:',
    'TEST',
    'Hard',
    'Senior',
    10,
    4
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '52e63c34-ce06-5119-b6be-43415bf3b56e',
    '7c090048-602d-5f32-93da-d64e07d36b29',
    'При создании таблицы можно задать первичный и внешний ключи',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '633022f1-3cfd-5e8e-ad3b-5c4b9831cf3a',
    '7c090048-602d-5f32-93da-d64e07d36b29',
    'Оператор TRUNCATE удаляет все строки таблицы, записывая их в журнал транзакций',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'a5544504-a783-5761-a4e4-d12e2fea7dc8',
    '7c090048-602d-5f32-93da-d64e07d36b29',
    'При создании таблицы можно задать только внешний ключ',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '9c918b3c-6f57-517e-ba1f-d556630d51cd',
    '7c090048-602d-5f32-93da-d64e07d36b29',
    'При наличии внешних ключей можно определить поведение записи при изменении или удалении зависимой от нее записи в другой таблице.',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '8b26fd92-7837-59ff-b18c-108cf0bbf6af',
    '7c090048-602d-5f32-93da-d64e07d36b29',
    'Оператор DROP TABLE удаляет таблицу из базы данных со всеми записями',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'a2e71d5d-ffdb-51ba-90b4-2d4cf467195e',
    '7c090048-602d-5f32-93da-d64e07d36b29',
    'Удалить записи из таблицы, сохранив саму таблицу в базе данных, можно с помощью операторов DELETE и TRUNCATE',
    true
);

-- 22. SQL · Тест 10
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '406a62db-382c-5b96-ba7a-2455240ed72c',
    'SQL · Тест 10',
    '10. Отметьте верные утверждения относительно порядка вывода отфильтрованных в результате SQL-запроса строк:',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'de7be521-1bec-5409-9171-4ce213056d00',
    '406a62db-382c-5b96-ba7a-2455240ed72c',
    'При выполнении SQL-запроса на выборку (SELECT) строки возвращаются в порядке их записи в базу данных',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '539b9db6-ead9-53ca-b02e-46ef00389698',
    '406a62db-382c-5b96-ba7a-2455240ed72c',
    'Фактический порядок строк в результате выполнения SQL-запроса зависит от плана его оптимизации и расположения данных на диске',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '28632eae-e757-5d61-843f-6139b3fff822',
    '406a62db-382c-5b96-ba7a-2455240ed72c',
    'Сортировка возможна только по одному полю (столбцу)',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '9cb7120e-d5ad-54cd-aca8-4e21555c6058',
    '406a62db-382c-5b96-ba7a-2455240ed72c',
    'По умолчанию в операторе ORDER BY применяется сортировка по возрастанию',
    false
);

-- 23. SQL · Тест 11
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '5a7cdedd-23e6-5c38-9a50-63bcd48b9af6',
    'SQL · Тест 11',
    '11. Какое ключевое слово используется для выбора всех столбцов из таблицы?',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'fbc33c75-7cec-5822-b0b5-5815c5f52f75',
    '5a7cdedd-23e6-5c38-9a50-63bcd48b9af6',
    'SELECT *',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '623e9c55-8c18-5b88-943c-487aafc3d02f',
    '5a7cdedd-23e6-5c38-9a50-63bcd48b9af6',
    'SELECT ALL',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '42a7740d-9a87-534e-9832-afd5e666595a',
    '5a7cdedd-23e6-5c38-9a50-63bcd48b9af6',
    'GET *',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '115d16f4-5bf7-5fa5-934b-f32d923964d1',
    '5a7cdedd-23e6-5c38-9a50-63bcd48b9af6',
    'FETCH *',
    false
);

-- 24. SQL · Тест 12
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    'ad920d97-13d5-576b-8615-8e4617ea317a',
    'SQL · Тест 12',
    '12. Какие из следующих операторов используются для фильтрации строк в SQL-запросе?',
    'TEST',
    'Medium',
    'Middle',
    10,
    2
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '6c8d8dfb-9eed-5323-ab3a-0b4d8afbd101',
    'ad920d97-13d5-576b-8615-8e4617ea317a',
    'WHERE',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'd47291c1-c37f-5340-9e9c-73b3ea67f662',
    'ad920d97-13d5-576b-8615-8e4617ea317a',
    'HAVING',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'e4ce21d2-c02e-5fa3-b292-b08db3172459',
    'ad920d97-13d5-576b-8615-8e4617ea317a',
    'GROUP BY',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'ec20a149-b588-53fb-b28a-92825af41e0c',
    'ad920d97-13d5-576b-8615-8e4617ea317a',
    'ORDER BY',
    false
);

-- 25. SQL · Тест 13
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '244ee711-0698-54cc-a3bc-df128069db5b',
    'SQL · Тест 13',
    '13. Какая функция SQL используется для подсчета количества строк в таблице?',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '95290b24-179e-5482-88fb-a4803d1131d4',
    '244ee711-0698-54cc-a3bc-df128069db5b',
    'COUNT()',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '2c350b25-b9b1-5e7b-a875-58765d32c551',
    '244ee711-0698-54cc-a3bc-df128069db5b',
    'SUM()',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '10d83b9e-3be5-5853-aefa-9efab208bede',
    '244ee711-0698-54cc-a3bc-df128069db5b',
    'AVG()',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '9b55b83b-7de4-560a-95e3-cfc6396e860d',
    '244ee711-0698-54cc-a3bc-df128069db5b',
    'MAX()',
    false
);

-- 26. Мат. статистика · Тест 1
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    'c7b2a18a-a73d-5196-bac9-f738c27c3e5e',
    'Мат. статистика · Тест 1',
    '1. Предметом математической статистики является изучение ...',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'd87e1d73-cbdb-5632-9199-a444cceafc0c',
    'c7b2a18a-a73d-5196-bac9-f738c27c3e5e',
    'случайных величин по результатам наблюдений;',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'f4d3cf86-b1e7-5047-bf20-5b1a97812a45',
    'c7b2a18a-a73d-5196-bac9-f738c27c3e5e',
    'случайных явлений;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'f146085e-7059-58de-9005-f2fc4024da37',
    'c7b2a18a-a73d-5196-bac9-f738c27c3e5e',
    'совокупностей;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'b2df8fe5-dda2-53e2-bd60-9a41ebbf65c9',
    'c7b2a18a-a73d-5196-bac9-f738c27c3e5e',
    'числовых характеристик.',
    false
);

-- 27. Мат. статистика · Тест 2
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '2fc7e711-c00e-5fb2-87ad-4d2b993f692e',
    'Мат. статистика · Тест 2',
    '2. Совокупность всех возможных объектов данного вида, над которыми проводятся наблюдения с целью получения конкретных значений определенной случайной величины называется …',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '35daf209-9048-5693-aa1e-c78bf7958ee2',
    '2fc7e711-c00e-5fb2-87ad-4d2b993f692e',
    'выборкой;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'd8594302-be5a-5c35-bb99-137969217b14',
    '2fc7e711-c00e-5fb2-87ad-4d2b993f692e',
    'вариантами;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '692890df-fe43-5168-b7a8-4af30c368bf8',
    '2fc7e711-c00e-5fb2-87ad-4d2b993f692e',
    'генеральной совокупностью;',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '2e8e7c19-ff34-5fac-bec8-d316777bc40e',
    '2fc7e711-c00e-5fb2-87ad-4d2b993f692e',
    'выборочной совокупностью.',
    false
);

-- 28. Мат. статистика · Тест 3
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '7dda8d9f-063a-5c56-908a-9a440715f81f',
    'Мат. статистика · Тест 3',
    '3. Выберите номер неправильного ответа. Генеральные совокупности могут быть:',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '2be68a8f-786d-500b-b142-df412b0873eb',
    '7dda8d9f-063a-5c56-908a-9a440715f81f',
    'конечными;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '6bc4eed2-690c-51dc-bb73-ee8e63803145',
    '7dda8d9f-063a-5c56-908a-9a440715f81f',
    'бесконечными;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '7d794101-7210-5081-8023-6ef26fbbe522',
    '7dda8d9f-063a-5c56-908a-9a440715f81f',
    'интервальными;',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '929aaa1c-af37-540f-a3ec-e4026abaf948',
    '7dda8d9f-063a-5c56-908a-9a440715f81f',
    'счетными.',
    false
);

-- 29. Мат. статистика · Тест 4
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    'a594bf07-2100-55ac-a91a-82d83c8ddcfe',
    'Мат. статистика · Тест 4',
    '4. Часть отобранных объектов из генеральной совокупности называется:',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '476c8e45-7cdb-5201-948e-1cb9a2f719b3',
    'a594bf07-2100-55ac-a91a-82d83c8ddcfe',
    'генеральной выборкой;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '3ea37290-efc2-569a-9cf2-c1882daa524b',
    'a594bf07-2100-55ac-a91a-82d83c8ddcfe',
    'выборочной совокупностью;',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '5e8865ca-f222-5397-afaf-1d61298f91de',
    'a594bf07-2100-55ac-a91a-82d83c8ddcfe',
    'репрезентативной совокупностью;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '288a7380-bf27-5bde-9939-1dcd11886323',
    'a594bf07-2100-55ac-a91a-82d83c8ddcfe',
    'вариантами.',
    false
);

-- 30. Мат. статистика · Тест 5
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '6f86f423-4cbd-5414-a0d8-be85225496a9',
    'Мат. статистика · Тест 5',
    '5. Для того, чтобы по выборке можно было судить о случайной величине, выборка должна быть …',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '81c7024d-d60a-5f9d-85de-fc3cb541c6d8',
    '6f86f423-4cbd-5414-a0d8-be85225496a9',
    'бесповторной;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'cb423ce9-a4f2-59e1-b655-7cecfaee672c',
    '6f86f423-4cbd-5414-a0d8-be85225496a9',
    'повторной;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '49f5b6f6-0f08-5665-a057-00b3e2fcd199',
    '6f86f423-4cbd-5414-a0d8-be85225496a9',
    'безвозвратной;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '2c30a21f-3025-5278-af1e-4890fdb0e076',
    '6f86f423-4cbd-5414-a0d8-be85225496a9',
    'репрезентативной.',
    true
);

-- 31. Мат. статистика · Тест 6
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    'c3c3495d-f415-5c2a-86d4-2824306c3aa4',
    'Мат. статистика · Тест 6',
    '6. Репрезентативность выборки обеспечивается:',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'c51eaf15-0229-53ad-a796-b14210b5aeb4',
    'c3c3495d-f415-5c2a-86d4-2824306c3aa4',
    'случайностью отбора;',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'd63003db-68db-55cb-b60d-4d9d9648dca9',
    'c3c3495d-f415-5c2a-86d4-2824306c3aa4',
    'таблицей;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '43467e30-30f5-5d36-9bd6-a07ba8dd5b80',
    'c3c3495d-f415-5c2a-86d4-2824306c3aa4',
    'вариацией;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '07f7885d-b4c3-5e0b-a312-475dd91606fa',
    'c3c3495d-f415-5c2a-86d4-2824306c3aa4',
    'группировкой.',
    false
);

-- 32. Мат. статистика · Тест 7
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '321644dd-8f28-5ea1-ada8-b69419868bd1',
    'Мат. статистика · Тест 7',
    '7. Если один и тот же объект генеральной совокупности может попасть в выборку дважды, то образованная таким образом выборочная совокупность называется:',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'f6809d1d-f03a-59a7-a203-03c33e104c6d',
    '321644dd-8f28-5ea1-ada8-b69419868bd1',
    'повторной;',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '75fef392-69c9-5e7b-9af1-0d17932302fa',
    '321644dd-8f28-5ea1-ada8-b69419868bd1',
    'бесповторной;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '9311f28b-6e37-5ce0-82ec-d6650f0c683f',
    '321644dd-8f28-5ea1-ada8-b69419868bd1',
    'частичной;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '274ecd6a-ab0e-50e8-95b9-055129598ebe',
    '321644dd-8f28-5ea1-ada8-b69419868bd1',
    'полной.',
    false
);

-- 33. Мат. статистика · Тест 8
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    'a70e4633-7a2f-59cd-9944-66e72d4867a9',
    'Мат. статистика · Тест 8',
    '8. Выберите номер неправильного ответа. Существуют следующие способы отбора выборочной совокупности:',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '20414e5e-78a9-590a-95e5-d877917914eb',
    'a70e4633-7a2f-59cd-9944-66e72d4867a9',
    'простой случайный;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'fd951f9c-be8a-57a8-a992-7134ef3a533c',
    'a70e4633-7a2f-59cd-9944-66e72d4867a9',
    'типический;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'e2bec985-c1dc-5477-a8eb-3c9c8087d78a',
    'a70e4633-7a2f-59cd-9944-66e72d4867a9',
    'механический;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '05b3d280-7a92-5d60-b75b-07b38a5e9ddd',
    'a70e4633-7a2f-59cd-9944-66e72d4867a9',
    'серийный;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '3aaeb1e2-b108-5636-9ed8-4b2408438824',
    'a70e4633-7a2f-59cd-9944-66e72d4867a9',
    'д) вариационный.',
    true
);

-- 34. Мат. статистика · Тест 9
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '8195d6e7-0b3a-5de8-8a67-f7f9d1a7e89c',
    'Мат. статистика · Тест 9',
    '9. Различные значения признака ( случайной величины Х) называются:',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'b8837e89-0c51-5339-b079-f3a72efe0c73',
    '8195d6e7-0b3a-5de8-8a67-f7f9d1a7e89c',
    'частостями;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'e7a41994-611e-509f-94d3-a0a5dcb2616a',
    '8195d6e7-0b3a-5de8-8a67-f7f9d1a7e89c',
    'частотами;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '67efd19d-b8a2-5789-86da-dd2e3c093c82',
    '8195d6e7-0b3a-5de8-8a67-f7f9d1a7e89c',
    'вариантами;',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'd86b0a0f-6350-5a33-ae20-133499965a57',
    '8195d6e7-0b3a-5de8-8a67-f7f9d1a7e89c',
    'выборкой.',
    false
);

-- 35. Мат. статистика · Тест 10
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '67810599-45ea-5e4e-afb4-9fcc183c194f',
    'Мат. статистика · Тест 10',
    '10. Ранжирование – это операция, заключающаяся в том, что наблюдаемые значения случайной величины располагают в порядке:',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'fea4eaac-cb79-50d1-90a3-bde9da204b1a',
    '67810599-45ea-5e4e-afb4-9fcc183c194f',
    'группирования;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'b35d5750-3281-5e61-a22d-79d29af469be',
    '67810599-45ea-5e4e-afb4-9fcc183c194f',
    'неубывания;',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '3be883f4-b732-54ed-a833-cf92b685996b',
    '67810599-45ea-5e4e-afb4-9fcc183c194f',
    'расположения;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '7e20fa0a-0442-5b50-9e58-944ecbe1ef5e',
    '67810599-45ea-5e4e-afb4-9fcc183c194f',
    'невозрастания.',
    false
);

-- 36. Мат. статистика · Тест 11
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    'ca4b4317-d2e6-51bc-aa4a-646b2cbff780',
    'Мат. статистика · Тест 11',
    '11. Разбивка вариант на отдельные интервалы называется:',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'bbbea2a1-2c88-5d9e-9b78-ab801da996c3',
    'ca4b4317-d2e6-51bc-aa4a-646b2cbff780',
    'варьированием;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '4a87d2f3-4499-5fb3-b336-2a126dad9f52',
    'ca4b4317-d2e6-51bc-aa4a-646b2cbff780',
    'ранжированием;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '186900f0-d4e6-5fc5-8af5-d58edacc344a',
    'ca4b4317-d2e6-51bc-aa4a-646b2cbff780',
    'сочетанием;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '0e6db0e4-1600-532f-a415-9932b11cc009',
    'ca4b4317-d2e6-51bc-aa4a-646b2cbff780',
    'группировкой.',
    true
);

-- 37. Мат. статистика · Тест 12
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '84dbe201-8f7a-5c61-b707-8dc3a97c015c',
    'Мат. статистика · Тест 12',
    '12. 3,1,3,1,4,2,2,4,0,3,0,2,2,0,2 – выборка. 0,1,2,3,4 - ?',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'ac2b67bd-fc9f-5d6f-a424-d20180a6d861',
    '84dbe201-8f7a-5c61-b707-8dc3a97c015c',
    'ряд;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '9301b3c1-513e-5fb8-a008-0d7dcfaee818',
    '84dbe201-8f7a-5c61-b707-8dc3a97c015c',
    'варианты;',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '34abb918-8df0-5413-b9f6-cedacf544c3f',
    '84dbe201-8f7a-5c61-b707-8dc3a97c015c',
    'частоты;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'c8aafe66-3b22-564d-886f-ce36262eb243',
    '84dbe201-8f7a-5c61-b707-8dc3a97c015c',
    'частости.',
    false
);

-- 38. Мат. статистика · Тест 13
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '9d383f3d-8264-505c-a79f-751d21e70558',
    'Мат. статистика · Тест 13',
    '13. Числа, показывающие, сколько раз встречаются варианты из данного интервала, называются:',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'b65b6588-d845-5ff3-b188-c8fa1ebee268',
    '9d383f3d-8264-505c-a79f-751d21e70558',
    'группами;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '7f40d282-a947-57ce-8899-d328c74bfd74',
    '9d383f3d-8264-505c-a79f-751d21e70558',
    'вариациями;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'c501c91e-b4b7-5895-a702-edd676e1ad8f',
    '9d383f3d-8264-505c-a79f-751d21e70558',
    'частотами;',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '64749fe3-23ca-55a7-8cf1-44601c3d865e',
    '9d383f3d-8264-505c-a79f-751d21e70558',
    'частостями.',
    false
);

-- 39. Мат. статистика · Тест 14
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '8d384d4a-fecf-5929-8a6b-4a90f9e5b8b8',
    'Мат. статистика · Тест 14',
    '14. 3,1,3,1,4,2,2,4,0,3,0,2,2,0,2 – выборка. Частота варианты 0 равна:',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '153c9f82-0e8d-5adb-a480-e2422e448248',
    '8d384d4a-fecf-5929-8a6b-4a90f9e5b8b8',
    '3;',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '9b815511-25d7-5ce2-b704-f7ebdf5673a8',
    '8d384d4a-fecf-5929-8a6b-4a90f9e5b8b8',
    '1/5;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '26f779ba-984d-544b-85bf-142ad26bd987',
    '8d384d4a-fecf-5929-8a6b-4a90f9e5b8b8',
    '5;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '23da43b2-79c4-593e-a288-9b603ad92d63',
    '8d384d4a-fecf-5929-8a6b-4a90f9e5b8b8',
    '1/3.',
    false
);

-- 40. Мат. статистика · Тест 15
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '3f8f1442-520c-5379-8d47-33c423786800',
    'Мат. статистика · Тест 15',
    '15. Отношение частоты данного варианта к общей сумме частот всех вариантов называется:',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'f9eb7111-23ef-529e-b29d-1e6270266e63',
    '3f8f1442-520c-5379-8d47-33c423786800',
    'группой;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '8f2ca809-9e17-5685-bedd-72d95fc792f7',
    '3f8f1442-520c-5379-8d47-33c423786800',
    'вариацией;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '0de50c7a-7d8d-575c-a592-2788187208e9',
    '3f8f1442-520c-5379-8d47-33c423786800',
    'частотой;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '0fc95bab-834a-5baf-b3e6-f6c4063ff8ab',
    '3f8f1442-520c-5379-8d47-33c423786800',
    'частостью.',
    true
);

-- 41. Мат. статистика · Тест 16
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '69e994f3-1584-539f-aa7b-a5bb08267955',
    'Мат. статистика · Тест 16',
    '16. 3,1,3,1,4,2,2,4,0,3,0,2,2,0,2 – выборка. Частость варианты 2 составляет:',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '667ab7f2-81f9-56bd-a1be-7231decc0dc3',
    '69e994f3-1584-539f-aa7b-a5bb08267955',
    '5;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'a9f5f20a-f449-5449-b0f5-13519f32ab62',
    '69e994f3-1584-539f-aa7b-a5bb08267955',
    '1/3;',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '226f76a2-8858-53bd-a15e-c4ca1b325d81',
    '69e994f3-1584-539f-aa7b-a5bb08267955',
    '1/5;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'd3fc9983-3f4b-59c3-be6b-83ddc80b9cd2',
    '69e994f3-1584-539f-aa7b-a5bb08267955',
    '3.',
    false
);

-- 42. Мат. статистика · Тест 17
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    'a6f171d0-6715-5506-984c-68ddd9efe10f',
    'Мат. статистика · Тест 17',
    '17. Частоты и частости называют:',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'bd2529b2-d27d-5f78-8873-ce86db4d471a',
    'a6f171d0-6715-5506-984c-68ddd9efe10f',
    'выборкой;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '59244b57-51c5-555e-83fe-a4a97803ef5d',
    'a6f171d0-6715-5506-984c-68ddd9efe10f',
    'рядом;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '3da01220-5390-5b2a-9196-48f1de593189',
    'a6f171d0-6715-5506-984c-68ddd9efe10f',
    'весами;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '3a8f13e7-6905-5c1d-9665-298b28d72fea',
    'a6f171d0-6715-5506-984c-68ddd9efe10f',
    'характеристиками.',
    true
);

-- 43. Мат. статистика · Тест 18
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    'e8f1426c-6167-5578-9813-7f27983bfac2',
    'Мат. статистика · Тест 18',
    '18. 3,1,3,1,4,2,2,4,0,3,0,2,2,0,2 – выборка. 0,0,0,1,1,2,2,2,2,2,3,3,3,4,4 - ?',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '7efad4b5-544e-59c1-8610-8aa51a1f17c9',
    'e8f1426c-6167-5578-9813-7f27983bfac2',
    'ранжированный ряд;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '7eb22788-b098-567e-b230-3994e8d30845',
    'e8f1426c-6167-5578-9813-7f27983bfac2',
    'полигон;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '1d52dbe2-0802-5796-a3e1-605f8c77a88a',
    'e8f1426c-6167-5578-9813-7f27983bfac2',
    'группа;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '5bf7a294-1df6-5b0c-b981-dc54cddf3f5b',
    'e8f1426c-6167-5578-9813-7f27983bfac2',
    'вариационный ряд.',
    true
);

-- 44. Мат. статистика · Тест 19
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '856a4565-2bb4-5f30-b6d0-d47c91c49231',
    'Мат. статистика · Тест 19',
    '19. Ранжированный ряд вариантов с соответствующими им весами называют:',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '3ea9a0ca-d063-5af6-b01c-afa6ef14bcf2',
    '856a4565-2bb4-5f30-b6d0-d47c91c49231',
    'группировкой;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '15f65bc0-3ab9-5022-91b2-dffb7de2293e',
    '856a4565-2bb4-5f30-b6d0-d47c91c49231',
    'выборкой;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'e30d2256-f080-5840-8604-b39772dce548',
    '856a4565-2bb4-5f30-b6d0-d47c91c49231',
    'функцией;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '3ea2ff85-7d83-5149-b5ad-ca339568dd57',
    '856a4565-2bb4-5f30-b6d0-d47c91c49231',
    'вариационным рядом.',
    true
);

-- 45. Мат. статистика · Тест 20
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '559a09c7-6e2f-57c8-a00d-68e0ac4a0389',
    'Мат. статистика · Тест 20',
    '20. Если все варианты увеличить в одно и то же число раз, то средняя арифметическая …',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '5aecfbaf-6ebd-5230-a492-34e6385aa3fe',
    '559a09c7-6e2f-57c8-a00d-68e0ac4a0389',
    'увеличится на то же число;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '0bf134d6-71b5-5d58-9850-c60a32f3e000',
    '559a09c7-6e2f-57c8-a00d-68e0ac4a0389',
    'уменьшится во столько же раз;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'a9a7684e-14cb-5401-b394-5e45565f9c2b',
    '559a09c7-6e2f-57c8-a00d-68e0ac4a0389',
    'уменьшится на то же число;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '56a8948c-19fe-557d-a7e0-dcb9f6d563e4',
    '559a09c7-6e2f-57c8-a00d-68e0ac4a0389',
    'увеличится во столько же раз.',
    true
);

-- 46. Мат. статистика · Тест 21
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '8754ecfb-ebf3-5f3f-9d62-4bfa2d7d315b',
    'Мат. статистика · Тест 21',
    '21. Если все варианты уменьшить на одно и то же число, то средняя арифметическая …',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '672913b5-6fb4-52e0-adcc-4c9c7a5056a0',
    '8754ecfb-ebf3-5f3f-9d62-4bfa2d7d315b',
    'увеличится на то же число;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '0d6d50ab-d901-5348-b23a-e57ba53654ea',
    '8754ecfb-ebf3-5f3f-9d62-4bfa2d7d315b',
    'уменьшится во столько же раз;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '9da0aef0-5887-5b37-8598-73e34ec06e69',
    '8754ecfb-ebf3-5f3f-9d62-4bfa2d7d315b',
    'уменьшится на то же число;',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'd13fe21d-47c9-5fe2-9040-e5d000a32fe6',
    '8754ecfb-ebf3-5f3f-9d62-4bfa2d7d315b',
    'увеличится во столько же раз.',
    false
);

-- 47. Мат. статистика · Тест 22
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '5c4d371f-2452-5a31-8d53-441ee9e302db',
    'Мат. статистика · Тест 22',
    '22. Средняя арифметическая постоянной равна …',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '0595e21e-0b43-5002-8c1d-f542a9d347fb',
    '5c4d371f-2452-5a31-8d53-441ee9e302db',
    'самой постоянной;',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '3b493f50-b9e1-5ed2-bc95-556331271594',
    '5c4d371f-2452-5a31-8d53-441ee9e302db',
    'нулю;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '8fe57ac4-76ec-5ed3-868b-2080a413ec65',
    '5c4d371f-2452-5a31-8d53-441ee9e302db',
    'единице;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'a357a133-36c5-525d-af00-a8c0a7d18104',
    '5c4d371f-2452-5a31-8d53-441ee9e302db',
    'количеству измерений.',
    false
);

-- 48. Мат. статистика · Тест 23
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '1559765f-e080-585e-b15e-96dda11f398a',
    'Мат. статистика · Тест 23',
    '23. Если все частоты вариантов умножить на одно и то же число, то среднее арифметическое …',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '8053e565-40e7-595d-8714-dea03cf6d906',
    '1559765f-e080-585e-b15e-96dda11f398a',
    'увеличится во столько же раз;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '12c58a41-f6d9-5995-992c-2f24fee921c1',
    '1559765f-e080-585e-b15e-96dda11f398a',
    'не изменится;',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '2d9f9e1d-7f5e-50ab-98d1-5c2fe4000c6f',
    '1559765f-e080-585e-b15e-96dda11f398a',
    'уменьшится во столько же раз;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'aca5d43e-5208-5c8d-9505-a1b3b8b6fbff',
    '1559765f-e080-585e-b15e-96dda11f398a',
    'увеличится на такое же число.',
    false
);

-- 49. Мат. статистика · Тест 24
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    'd3cd1e04-2856-5780-8156-2b5c07e4eb18',
    'Мат. статистика · Тест 24',
    '24. Медианой вариационного ряда называется значение признака, приходящееся на … ранжированного ряда наблюдений.',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '0dcf0e38-445c-5250-a33b-dce1e066ee31',
    'd3cd1e04-2856-5780-8156-2b5c07e4eb18',
    'минимум;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'd6fb3a60-03ae-5953-8a4b-5d0cd0926b0a',
    'd3cd1e04-2856-5780-8156-2b5c07e4eb18',
    'максимум;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '89fca2c7-3ca1-5797-989b-d7caffb8fe0e',
    'd3cd1e04-2856-5780-8156-2b5c07e4eb18',
    'начало;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'f2a064a3-0698-5861-ba6f-9a13bbe97b0b',
    'd3cd1e04-2856-5780-8156-2b5c07e4eb18',
    'середину.',
    true
);

-- 50. Мат. статистика · Тест 25
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    'cb438d6c-abff-5e02-92c1-831b1d89ab6c',
    'Мат. статистика · Тест 25',
    '25. Дисперсия постоянной равна:',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '699fce5d-94e6-58c4-a511-f5343a9a3e55',
    'cb438d6c-abff-5e02-92c1-831b1d89ab6c',
    'самой постоянной;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '69d502ed-38a4-5bf8-83fa-ac003523badc',
    'cb438d6c-abff-5e02-92c1-831b1d89ab6c',
    'нулю;',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'f85f116e-7499-5d37-8443-cc302719aa3b',
    'cb438d6c-abff-5e02-92c1-831b1d89ab6c',
    'единице;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '9f0faa4f-c723-5777-87ce-eded00a3fa6d',
    'cb438d6c-abff-5e02-92c1-831b1d89ab6c',
    'не существует.',
    false
);

-- 51. Мат. статистика · Тест 26
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '67c8933f-8843-5639-b0e0-0d26470313ea',
    'Мат. статистика · Тест 26',
    '26. Если все варианты уменьшить на одно и то же число, то дисперсия ...',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '62aec13d-fc49-5b6e-be33-1becca13b923',
    '67c8933f-8843-5639-b0e0-0d26470313ea',
    'увеличится на то же число;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'b0beae9b-1a53-52b5-aef6-02f63a489505',
    '67c8933f-8843-5639-b0e0-0d26470313ea',
    'уменьшится на то же число;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '9a75b52d-ceaf-55cf-81aa-c4d349550d58',
    '67c8933f-8843-5639-b0e0-0d26470313ea',
    'не изменится;',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '688e2658-4cfc-5b87-9b95-af55f98396ca',
    '67c8933f-8843-5639-b0e0-0d26470313ea',
    'будет равна нулю.',
    false
);

-- 52. Мат. статистика · Тест 27
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '452d7fc8-8971-5593-94ba-ab50d84e23bd',
    'Мат. статистика · Тест 27',
    '27. Сущность выборочного метода состоит в том, что по некоторой части генеральной совокупности (по выборке) …',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '1a9f77c1-d9da-5515-b244-d1d4cbacf8b7',
    '452d7fc8-8971-5593-94ba-ab50d84e23bd',
    'можно выносит суждение о ее свойствах в целом;',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'd481eead-35c2-5a33-b8e9-775c49e8ca6a',
    '452d7fc8-8971-5593-94ba-ab50d84e23bd',
    'можно найти ее статистические характеристики;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '52949c40-9957-5437-86ae-0081d9dceb6b',
    '452d7fc8-8971-5593-94ba-ab50d84e23bd',
    'можно построить полигон или гистограмму относительных частот;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '921ef6e3-043b-56c2-a2fa-548bf9242300',
    '452d7fc8-8971-5593-94ba-ab50d84e23bd',
    'можно найти эмпирическую функцию распределения. 1 2 3 4 5 6 4 2 n х 1 2 3 4 6 4 2 х n',
    false
);

-- 53. Мат. статистика · Тест 28
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '5795fa24-0174-59ee-a582-be51640d6099',
    'Мат. статистика · Тест 28',
    '28. Выборочная характеристика, используемая в качестве приближенного значения неизвестной генеральной характеристики, называется ее:',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '531620b1-2fc5-5f5a-bf08-c981aff0d5fd',
    '5795fa24-0174-59ee-a582-be51640d6099',
    'статистической характеристикой;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '1b875868-a617-5dc6-b3b1-915178e378a7',
    '5795fa24-0174-59ee-a582-be51640d6099',
    'оценкой;',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '58c9f45b-8a68-5826-bc92-2278c4e88ca9',
    '5795fa24-0174-59ee-a582-be51640d6099',
    'статистической точечной оценкой;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '6d48fbe7-88d5-56d1-a592-5daff50af8f0',
    '5795fa24-0174-59ee-a582-be51640d6099',
    'состоятельной оценкой.',
    false
);

-- 54. Мат. статистика · Тест 29
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '440715db-ea0a-55a9-aed7-1421ec76ac9e',
    'Мат. статистика · Тест 29',
    '29. Оценка называется … , если ее математическое ожидание равно оцениваемому параметру.',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '55c67b94-2f63-5a8c-b4e0-0ed54b26b31a',
    '440715db-ea0a-55a9-aed7-1421ec76ac9e',
    'смещенной;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '3a097009-76d7-5e27-bcff-1dbc9e410546',
    '440715db-ea0a-55a9-aed7-1421ec76ac9e',
    'несмещенной;',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '2e748587-d69b-514a-96dd-5ee6e51cde23',
    '440715db-ea0a-55a9-aed7-1421ec76ac9e',
    'несостоятельной;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '0e905cb1-9123-56ca-b612-bff781218f12',
    '440715db-ea0a-55a9-aed7-1421ec76ac9e',
    'состоятельной.',
    false
);

-- 55. Мат. статистика · Тест 30
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '0b457276-b62f-5208-83c0-50a0f0e4e2ca',
    'Мат. статистика · Тест 30',
    '30. Выберите номер неправильного ответа. Требование несмещенности гарантирует:',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '3236d206-6197-5d0c-bb64-0015d189eacf',
    '0b457276-b62f-5208-83c0-50a0f0e4e2ca',
    'отсутствие систематических ошибок;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'faf15e6d-17d8-54f7-8e06-4849512a82fc',
    '0b457276-b62f-5208-83c0-50a0f0e4e2ca',
    'несостоятельность оценки;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'e4e78df5-2134-55bd-87f2-e63a4af8305d',
    '0b457276-b62f-5208-83c0-50a0f0e4e2ca',
    'состоятельность оценки.',
    true
);

-- 56. Мат. статистика · Тест 31
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '6baae0a9-e43a-59d6-a794-7d60d84933a9',
    'Мат. статистика · Тест 31',
    '31. Оценка называется эффективной, если она среди всех прочих несмещенных оценок той же самой характеристики обладает …',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '3f109b2d-ae96-5cc4-8186-8091d09add18',
    '6baae0a9-e43a-59d6-a794-7d60d84933a9',
    'наименьшей дисперсией;',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '5eaa8ce3-1b1b-50d7-ac69-477911cf9b5a',
    '6baae0a9-e43a-59d6-a794-7d60d84933a9',
    'наибольшей дисперсией;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '7c19bf4f-352f-5e66-a691-4bee3031cd05',
    '6baae0a9-e43a-59d6-a794-7d60d84933a9',
    'наименьшим математическим ожиданием;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'ed1b99e9-3ac7-5ca0-ae51-a2c0a449ba91',
    '6baae0a9-e43a-59d6-a794-7d60d84933a9',
    'наибольшим математическим ожиданием.',
    false
);

-- 57. Мат. статистика · Тест 32
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '2b3efb0c-972f-5e1b-bfc3-c101ac3de1cb',
    'Мат. статистика · Тест 32',
    '32. Выберите номер неправильного ответа. Методы нахождения точечных оценок:',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'c5321058-2be1-5aea-b7f0-68194a28bf85',
    '2b3efb0c-972f-5e1b-bfc3-c101ac3de1cb',
    'метод моментов;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '9c0d7d0f-b5ef-5e29-8020-1713650783ea',
    '2b3efb0c-972f-5e1b-bfc3-c101ac3de1cb',
    'метод наибольшего правдоподобия;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '24c61a8e-a575-51af-96dd-bdb4579336d2',
    '2b3efb0c-972f-5e1b-bfc3-c101ac3de1cb',
    'метод наименьших квадратов;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'c93b293b-6a76-55e4-995c-b230668f94cb',
    '2b3efb0c-972f-5e1b-bfc3-c101ac3de1cb',
    'метод оценок.',
    true
);

-- 58. Мат. статистика · Тест 33
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '979156d8-49af-5af7-b405-64d8a81c3b03',
    'Мат. статистика · Тест 33',
    '33. Какая мера центральной тенденции чувствительна к выбросам в данных?',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'd624d6e1-9c86-5ec3-9de5-65a4ca5991c9',
    '979156d8-49af-5af7-b405-64d8a81c3b03',
    'Среднее арифметическое',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '261ae52d-f932-5a14-bb6d-d9957f4f406d',
    '979156d8-49af-5af7-b405-64d8a81c3b03',
    'Медиана',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '98948c76-0b86-52c7-a61e-dd220789a662',
    '979156d8-49af-5af7-b405-64d8a81c3b03',
    'Мода',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'de55aa2b-2dea-5b9e-abc7-7d0674d9efeb',
    '979156d8-49af-5af7-b405-64d8a81c3b03',
    'Все вышеперечисленные',
    false
);

-- 59. Мат. статистика · Тест 34
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '6e8ba2ff-d6a4-520d-8c09-828a5e851c91',
    'Мат. статистика · Тест 34',
    '34. Какие из следующих мер являются мерами разброса (изменчивости) данных?',
    'TEST',
    'Medium',
    'Senior',
    10,
    2
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '0263046e-f962-52e5-a5e3-3737bb8419fc',
    '6e8ba2ff-d6a4-520d-8c09-828a5e851c91',
    'Дисперсия',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '966a6ac3-6f64-5a1a-b903-4155294649d8',
    '6e8ba2ff-d6a4-520d-8c09-828a5e851c91',
    'Стандартное отклонение',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '15e96fb7-7e14-5524-bd8f-94fe0d87dd68',
    '6e8ba2ff-d6a4-520d-8c09-828a5e851c91',
    'Квартиль',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '73257602-1ac0-5241-ba18-54e3a82d0367',
    '6e8ba2ff-d6a4-520d-8c09-828a5e851c91',
    'Среднее арифметическое',
    false
);

-- 60. Мат. статистика · Тест 35
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '824dfab4-9298-51c3-bb70-f7e6e294f626',
    'Мат. статистика · Тест 35',
    '35. Если p-значение (p-value) меньше уровня значимости (alpha, α), то:',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '7d30f000-28da-5b6e-9ed4-24caa184e7a3',
    '824dfab4-9298-51c3-bb70-f7e6e294f626',
    'Мы отвергаем нулевую гипотезу.',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '652f52e5-1f4a-5a84-ba90-daa545daeb73',
    '824dfab4-9298-51c3-bb70-f7e6e294f626',
    'Мы принимаем нулевую гипотезу.',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '416a3963-3044-5818-b1c6-7b117dcdbc9a',
    '824dfab4-9298-51c3-bb70-f7e6e294f626',
    'Мы не можем сделать вывод.',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '989c860b-0241-50e8-a4e6-f73e33ed4aba',
    '824dfab4-9298-51c3-bb70-f7e6e294f626',
    'Мы отвергаем альтернативную гипотезу.',
    false
);

-- 61. Pandas · Тест 1
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '827981d5-cd6c-56ea-bdee-f5889110a798',
    'Pandas · Тест 1',
    '1. Какой метод Pandas используется для чтения данных из CSV-файла в DataFrame?',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '092464a4-15b8-51ed-906d-5ee52610d360',
    '827981d5-cd6c-56ea-bdee-f5889110a798',
    'pd.read_csv()',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '64c38c0e-7586-5a66-987d-3edf355557b8',
    '827981d5-cd6c-56ea-bdee-f5889110a798',
    'pd.load_csv()',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'a40fc4ce-aa9b-5e5c-9aa5-e096f3d32e2b',
    '827981d5-cd6c-56ea-bdee-f5889110a798',
    'pd.get_csv()',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'ad496212-d15a-5063-9561-d8b20061d0dc',
    '827981d5-cd6c-56ea-bdee-f5889110a798',
    'pd.parse_csv()',
    false
);

-- 62. Pandas · Тест 2
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '3a1a34ab-a068-52b2-9a20-a5665bf4fb77',
    'Pandas · Тест 2',
    '2. Какие из следующих методов используются для выбора строк в DataFrame Pandas?',
    'TEST',
    'Medium',
    'Middle',
    10,
    2
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'eec9a431-5398-514c-a373-c7ddae5fafeb',
    '3a1a34ab-a068-52b2-9a20-a5665bf4fb77',
    'df.loc[]',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'f1dba4d5-a9a8-5835-bf7f-690ab857ede2',
    '3a1a34ab-a068-52b2-9a20-a5665bf4fb77',
    'df.iloc[]',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '050056b2-9266-53ef-a0e9-9a604b91b063',
    '3a1a34ab-a068-52b2-9a20-a5665bf4fb77',
    'df.head()',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '6b65a542-b3c3-5b3c-9756-f83c18559f3e',
    '3a1a34ab-a068-52b2-9a20-a5665bf4fb77',
    'df.tail()',
    false
);

-- 63. Pandas · Тест 3
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '3cf3cce1-5f1e-5c41-a410-dda1b0cb6003',
    'Pandas · Тест 3',
    '3. Какой метод Pandas используется для удаления строк или столбцов из DataFrame?',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'e345daf9-6350-54fe-97aa-726ead4b4fed',
    '3cf3cce1-5f1e-5c41-a410-dda1b0cb6003',
    'df.drop()',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'b081f929-f2e7-5288-aaf0-ef13c3f40ea9',
    '3cf3cce1-5f1e-5c41-a410-dda1b0cb6003',
    'df.remove()',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'ea7217ac-f337-5bee-b6b5-c2aeabe36a9d',
    '3cf3cce1-5f1e-5c41-a410-dda1b0cb6003',
    'df.delete()',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '9bb24c6e-2a52-5a70-b91b-9fb579fa6254',
    '3cf3cce1-5f1e-5c41-a410-dda1b0cb6003',
    'df.exclude()',
    false
);

-- 64. BPMN · Тест 1
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '04ad3022-0bff-5390-a310-7c2c42e503f3',
    'BPMN · Тест 1',
    '1. Какой элемент BPMN используется для обозначения начала или конца процесса?',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'd40bbe71-16fa-59b3-b8b6-d152897ff21e',
    '04ad3022-0bff-5390-a310-7c2c42e503f3',
    'Событие (Event)',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '7e918bcb-8350-588b-9762-6b57be136408',
    '04ad3022-0bff-5390-a310-7c2c42e503f3',
    'Задача (Task)',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '9f87f032-f669-5c19-a8ca-c44d11c705c8',
    '04ad3022-0bff-5390-a310-7c2c42e503f3',
    'Шлюз (Gateway)',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '6fa8455b-34fd-55ad-9641-ff6f3a4b7151',
    '04ad3022-0bff-5390-a310-7c2c42e503f3',
    'Поток управления ( Sequence Flow)',
    false
);

-- 65. BPMN · Тест 2
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '422e50a4-6f07-5502-b042-6c31d9da1cec',
    'BPMN · Тест 2',
    '2. Какие из следующих элементов BPMN используются для управления потоком выполнения процесса?',
    'TEST',
    'Medium',
    'Senior',
    10,
    2
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '045a8e39-d73c-5bdf-b6c6-986c6230bd06',
    '422e50a4-6f07-5502-b042-6c31d9da1cec',
    'Шлюз (Gateway)',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'a6d9139e-659c-58e8-bee3-4f35c09cc43e',
    '422e50a4-6f07-5502-b042-6c31d9da1cec',
    'Задача (Task)',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '978a24ab-a180-56fe-ba62-3b5b2ad765c4',
    '422e50a4-6f07-5502-b042-6c31d9da1cec',
    'Событие (Event)',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '4c1f05af-c1ca-520d-9fc8-b204bf86e3e2',
    '422e50a4-6f07-5502-b042-6c31d9da1cec',
    'Поток управления (Sequence Flow)',
    true
);

-- 66. BPMN · Тест 3
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    'b3e15222-2158-5fd0-a0ad-4c4fb112aa95',
    'BPMN · Тест 3',
    '3. Какой тип шлюза в BPMN используется для параллельного выполнения нескольких ветвей процесса?',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'd4a7fdbc-d5a4-5b6e-a840-c2cb286ea8a9',
    'b3e15222-2158-5fd0-a0ad-4c4fb112aa95',
    'Параллельный шлюз (Parallel Gateway)',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '450add22-56e6-5e98-bd7f-57e47a4c1c35',
    'b3e15222-2158-5fd0-a0ad-4c4fb112aa95',
    'Эксклюзивный шлюз ( Exclusive Gateway)',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'a0261aa6-4728-529b-afcc-64023f624f8d',
    'b3e15222-2158-5fd0-a0ad-4c4fb112aa95',
    'Включающий шлюз ( Inclusive Gateway)',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '9dc880ea-ed1f-594a-b2ba-725c99df186c',
    'b3e15222-2158-5fd0-a0ad-4c4fb112aa95',
    'Событийный шлюз (Event- based Gateway)',
    false
);

-- 67. Визуализация данных (Matplotlib/Seaborn) · Тест 1
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    'c690f3b7-a866-5791-a55e-1ffdcb32a807',
    'Визуализация данных (Matplotlib/Seaborn) · Тест 1',
    '1. Какая библиотека Python чаще всего используется для создания статичных, интерактивных и анимированных визуализаций в Python?',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'cdff7f44-6d8f-5b75-ba83-15f2b0305326',
    'c690f3b7-a866-5791-a55e-1ffdcb32a807',
    'Matplotlib',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '790f65a8-4fcf-5255-96ce-36235f868112',
    'c690f3b7-a866-5791-a55e-1ffdcb32a807',
    'Seaborn',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '1e1f521a-9552-5ec7-b2e7-3ab260dae99f',
    'c690f3b7-a866-5791-a55e-1ffdcb32a807',
    'Plotly',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'a9ae8a07-f99f-5ff5-9b6d-a3b8e048ea8f',
    'c690f3b7-a866-5791-a55e-1ffdcb32a807',
    'Bokeh',
    false
);

-- 68. Визуализация данных (Matplotlib/Seaborn) · Тест 2
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    'c559932e-a81c-5209-811c-4d80bb2e8396',
    'Визуализация данных (Matplotlib/Seaborn) · Тест 2',
    '2. Какие из следующих типов графиков подходят для отображения распределения одной числовой переменной?',
    'TEST',
    'Medium',
    'Middle',
    10,
    2
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'bbd19f0f-b64f-55df-bdb5-e69b459fd30a',
    'c559932e-a81c-5209-811c-4d80bb2e8396',
    'Гистограмма (Histogram)',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '7e2a8851-2cea-55ad-93a6-bfde82136900',
    'c559932e-a81c-5209-811c-4d80bb2e8396',
    'Ящичковая диаграмма (Box Plot)',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '0d6876dd-ce63-507d-a1ef-b30b388c69dc',
    'c559932e-a81c-5209-811c-4d80bb2e8396',
    'Диаграмма рассеяния ( Scatter Plot)',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '2f158a83-bde2-57b1-8d42-1d1afe8bd869',
    'c559932e-a81c-5209-811c-4d80bb2e8396',
    'Столбчатая диаграмма ( Bar Chart)',
    false
);

-- 69. Визуализация данных (Matplotlib/Seaborn) · Тест 3
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    'b8d4850c-184b-5114-9980-d475e536bb6f',
    'Визуализация данных (Matplotlib/Seaborn) · Тест 3',
    'Какой метод в Matplotlib используется для отображения графика на экране?',
    'TEST',
    'Easy',
    'Junior',
    10,
    1
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '7814c7bf-6e03-5f59-9b1d-fe176069a9b1',
    'b8d4850c-184b-5114-9980-d475e536bb6f',
    'plt.show()',
    true
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '9d5a4524-ba16-53d4-aeff-668c97a2c9c6',
    'b8d4850c-184b-5114-9980-d475e536bb6f',
    'plt.display()',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'f0a771d3-daa2-5b8e-a383-c780dc152e3c',
    'b8d4850c-184b-5114-9980-d475e536bb6f',
    'plt.render()',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '4df3e364-0cdc-5dd5-ad02-0876020d4913',
    'b8d4850c-184b-5114-9980-d475e536bb6f',
    'plt.plot()',
    false
);

-- 70. SQL · Исправление ошибки 1
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '392ca45e-185c-54b5-8c5e-c157ac7c4de7',
    'SQL · Исправление ошибки 1',
    '1. Найдите и исправьте ошибку в следующем SQL-запросе:
SELECT name, age
FROM users
WHERE age > 18 AND city = ''New York''
ORDER age DESC;',
    'OPEN',
    'Hard',
    'Middle',
    20,
    0
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '885edd6e-e7a4-5d61-ae66-10aa1d1a65d9',
    '392ca45e-185c-54b5-8c5e-c157ac7c4de7',
    'SELECT name, age
FROM users
WHERE age > 18 AND city = ''New York''
ORDER BY age DESC;',
    true
);

-- 71. SQL · Открытый вопрос 1
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    'a5992820-079a-5074-94f0-a602339d6de6',
    'SQL · Открытый вопрос 1',
    '1. Напишите SQL-запрос, который выберет имена всех сотрудников из таблицы employees, чья зарплата больше 50000 и которые работают в отделе ''Sales''.',
    'OPEN',
    'Medium',
    'Senior',
    20,
    0
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '9e412ac3-3efc-595f-9246-ea780b520458',
    'a5992820-079a-5074-94f0-a602339d6de6',
    'SELECT employee_name
FROM employees
WHERE salary > 50000 AND department = ''Sales'';',
    true
);

-- 72. SQL · Открытый вопрос 2
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '720febcb-df2e-5188-8a3a-2e9acda910c7',
    'SQL · Открытый вопрос 2',
    '2. Какое число будет выведено в результате выполнения следующего SQL-запроса, если в таблице orders есть 150 записей?
SELECT COUNT(*) / 3
FROM orders;',
    'OPEN',
    'Hard',
    'Middle',
    20,
    0
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '976d10c3-aa77-51bb-aef0-72c8490ba3b6',
    '720febcb-df2e-5188-8a3a-2e9acda910c7',
    '50',
    true
);

-- 73. Мат. статистика · Исправление ошибки 1
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    'ff1c228f-e2b5-51c7-b414-e627320c7c20',
    'Мат. статистика · Исправление ошибки 1',
    '1. Представьте, что вы анализируете данные о росте людей. У вас есть набор данных, и вы хотите рассчитать стандартное отклонение. Вы используете следующую формулу, но в ней есть ошибка:

Формула:
$$ \sigma = \sqrt{\frac{\sum_{i=1}^{n} (x_i - \mu)^2}{n}} $$
где $x_i$ — каждое значение, $\mu$ — среднее значение, $n$ — количество наблюдений.',
    'OPEN',
    'Medium',
    'Senior',
    20,
    0
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'dcd64762-b985-5fda-95c6-9aa9dad94a58',
    'ff1c228f-e2b5-51c7-b414-e627320c7c20',
    '$$ s = \sqrt{\frac{\sum_{i=1}^{n} (x_i - \bar{x})^2}{n-1}} $$
где $\bar{x}$ — выборочное среднее.',
    true
);

-- 74. Мат. статистика · Открытый вопрос 1
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    'd53698d9-b1e7-5f5e-99d4-8b06c9b2a3b5',
    'Мат. статистика · Открытый вопрос 1',
    '1. Вам дан набор данных с 100 наблюдениями. Среднее значение равно 50, а стандартное отклонение равно 10. Если вы увеличите каждое наблюдение на 5, каким будет новое среднее значение?',
    'OPEN',
    'Hard',
    'Middle',
    20,
    0
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '13908f64-d5a7-53bd-be56-7024efc1ec0b',
    'd53698d9-b1e7-5f5e-99d4-8b06c9b2a3b5',
    '55',
    true
);

-- 75. Мат. статистика · Открытый вопрос 2
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    'cc68303d-0523-586e-9eae-21c178f7ddb1',
    'Мат. статистика · Открытый вопрос 2',
    '2. Вам дан набор данных из 5 чисел: 10, 12, 15, 18, 20. Каково медианное значение этого набора данных?',
    'OPEN',
    'Medium',
    'Senior',
    20,
    0
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'd8fcdf45-7160-5e7a-93b0-bbb5883266f8',
    'cc68303d-0523-586e-9eae-21c178f7ddb1',
    '15',
    true
);

-- 76. Pandas · Исправление ошибки 1
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    'e94062cd-6809-5bb8-9b87-e508bbb5a3cb',
    'Pandas · Исправление ошибки 1',
    '1. Найдите и исправьте ошибку в следующем коде Python с использованием Pandas:
import pandas as pd

data = {''col1'': [1, 2, 3], ''col2'': [''A'', ''B'', ''C'']}
df = pd.DataFrame(data)

# Попытка выбрать столбец ''col3''
selected_column = df.col3
print(selected_column)',
    'OPEN',
    'Hard',
    'Middle',
    20,
    0
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '11b695c7-777a-5448-91e6-63edb3508e71',
    'e94062cd-6809-5bb8-9b87-e508bbb5a3cb',
    'import pandas as pd

data = {''col1'': [1, 2, 3], ''col2'': [''A'', ''B'', ''C'']}
df = pd.DataFrame(data)

# Выбор существующего столбца ''col1''
selected_column = df[''col1''] # Или df.col1, если имя столбца допустимо как атрибут
print(selected_column)',
    true
);

-- 77. Pandas · Исправление ошибки 2
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '9025ca77-5ed1-59a0-8a18-01b888ed74af',
    'Pandas · Исправление ошибки 2',
    '2. Найдите и исправьте ошибку в следующем коде Python с использованием Pandas:
import pandas as pd

data = {''name'': [''Alice'', ''Bob'', ''Charlie''], ''score'': [85, 92, 78]}
df = pd.DataFrame(data)

# Попытка отфильтровать строки, где score > 90
filtered_df = df.score > 90
print(filtered_df)',
    'OPEN',
    'Medium',
    'Senior',
    20,
    0
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '3cdc0d1c-cec6-55b6-8a96-52e200c3ff16',
    '9025ca77-5ed1-59a0-8a18-01b888ed74af',
    'import pandas as pd

data = {''name'': [''Alice'', ''Bob'', ''Charlie''], ''score'': [85, 92, 78]}
df = pd.DataFrame(data)

# Фильтрация строк, где score > 90
filtered_df = df[df.score > 90]
print(filtered_df)',
    true
);

-- 78. Pandas · Открытый вопрос 1
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '7b3d10cc-2efe-508e-bc54-2641e481ee3d',
    'Pandas · Открытый вопрос 1',
    '1. Дан DataFrame df со столбцами ''product_id'' и ''price''. Напишите код на Python с использованием Pandas, который вычислит среднюю цену всех продуктов.',
    'OPEN',
    'Hard',
    'Middle',
    20,
    0
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'f35b40b0-b7c9-5592-8152-a45e04c3bd87',
    '7b3d10cc-2efe-508e-bc54-2641e481ee3d',
    'average_price = df[''price''].mean()
print(average_price)',
    true
);

-- 79. Pandas · Открытый вопрос 2
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '99ab18fb-f924-573f-8dd2-56c29fbb977e',
    'Pandas · Открытый вопрос 2',
    '2. Дан DataFrame df со столбцами ''category'' и ''sales''. Сколько уникальных категорий продуктов содержится в DataFrame? Напишите код на Python с использованием Pandas для получения этого числа.',
    'OPEN',
    'Medium',
    'Senior',
    20,
    0
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '95c22e37-0dd7-5c31-a139-f3918ab6ed42',
    '99ab18fb-f924-573f-8dd2-56c29fbb977e',
    'num_unique_categories = df[''category''].nunique()
print(num_unique_categories)',
    true
);

-- 80. BPMN · Открытый вопрос 1
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '397094d7-999d-5cd5-9241-66fdfeba1352',
    'BPMN · Открытый вопрос 1',
    '1. В BPMN, какой символ используется для обозначения **задачи (Task)**?',
    'OPEN',
    'Hard',
    'Middle',
    20,
    0
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'cc111daa-46b6-53f2-9e2c-d26de649f94e',
    '397094d7-999d-5cd5-9241-66fdfeba1352',
    'Прямоугольник с закругленными углами.',
    true
);

-- 81. Визуализация данных (Matplotlib/Seaborn) · Исправление ошибки 1
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '7c140de9-142b-50c9-b0db-a569248d94d4',
    'Визуализация данных (Matplotlib/Seaborn) · Исправление ошибки 1',
    'Найдите и исправьте ошибку в следующем коде Python с использованием Matplotlib:
import matplotlib.pyplot as plt
import numpy as np

x = np.linspace(0, 10, 100)
y = np.sin(x)

plt.plot(x, y)
plt.xlabel("X-axis")
plt.ylabel("Y-axis")
plt.title("Sine Wave")',
    'OPEN',
    'Medium',
    'Senior',
    20,
    0
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '3f8865e6-4057-5395-96a8-192472dc25ff',
    '7c140de9-142b-50c9-b0db-a569248d94d4',
    'import matplotlib.pyplot as plt
import numpy as np

x = np.linspace(0, 10, 100)
y = np.sin(x)

plt.plot(x, y)
plt.xlabel("X-axis")
plt.ylabel("Y-axis")
plt.title("Sine Wave")
plt.show()',
    true
);

-- 82. Визуализация данных (Matplotlib/Seaborn) · Исправление ошибки 2
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '04bea691-cf1f-5102-925a-a472f7aff5a1',
    'Визуализация данных (Matplotlib/Seaborn) · Исправление ошибки 2',
    '2. Найдите и исправьте ошибку в следующем коде Python с использованием Seaborn для построения гистограммы:
import seaborn as sns
import matplotlib.pyplot as plt
import pandas as pd

data = {''values'': [1, 2, 2, 3, 3, 3, 4, 4, 5]}
df = pd.DataFrame(data)

sns.histplot(data=df, x=''value'') 
plt.show()',
    'OPEN',
    'Hard',
    'Middle',
    20,
    0
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'b3fdd065-45ab-59ec-adce-bbf588cc9810',
    '04bea691-cf1f-5102-925a-a472f7aff5a1',
    'import seaborn as sns
import matplotlib.pyplot as plt
import pandas as pd

data = {''values'': [1, 2, 2, 3, 3, 3, 4, 4, 5]}
df = pd.DataFrame(data)

sns.histplot(data=df, x=''values'') 
plt.show()',
    true
);

-- 83. Визуализация данных (Matplotlib/Seaborn) · Открытый вопрос 1
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '32c6252b-b0a9-55d9-8b0e-6ec5d6af20aa',
    'Визуализация данных (Matplotlib/Seaborn) · Открытый вопрос 1',
    '1. Напишите код на Python с использованием библиотеки Seaborn, который построит диаграмму рассеяния (scatter plot) для данных из DataFrame `df`, используя столбец `''feature1''` по оси X и столбец `''feature2''` по оси Y.',
    'OPEN',
    'Medium',
    'Senior',
    20,
    0
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'e8534519-0c12-5a84-a090-29181c2a1a1b',
    '32c6252b-b0a9-55d9-8b0e-6ec5d6af20aa',
    'import seaborn as sns
import matplotlib.pyplot as plt
import pandas as pd

# Предполагается, что df уже создан и содержит столбцы ''feature1'' и ''feature2''
# Пример создания df:
# data = {''feature1'': [1, 2, 3, 4, 5], ''feature2'': [5, 4, 3, 2, 1]}
# df = pd.DataFrame(data)

sns.scatterplot(data=df, x=''feature1'', y=''feature2'')
plt.show()',
    true
);

-- 84. Визуализация данных (Matplotlib/Seaborn) · Открытый вопрос 2
INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '8b1f8347-84fc-55be-8348-0c34f53701c2',
    'Визуализация данных (Matplotlib/Seaborn) · Открытый вопрос 2',
    '2. Дан DataFrame `df` со столбцом `''sales''`. Какая команда на Python с использованием Seaborn построит гистограмму распределения значений в этом столбце?',
    'OPEN',
    'Hard',
    'Middle',
    20,
    0
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '37e93fa8-0808-5668-9546-dcf782fcd11b',
    '8b1f8347-84fc-55be-8348-0c34f53701c2',
    'import seaborn as sns
import matplotlib.pyplot as plt
import pandas as pd

# Предполагается, что df уже создан и содержит столбец ''sales''
# Пример создания df:
# data = {''sales'': [100, 150, 120, 200, 180, 130, 250]}
# df = pd.DataFrame(data)

sns.histplot(data=df, x=''sales'')
plt.show()',
    true
);
