-- liquibase formatted sql

-- changeset nikolaj:add-questions

INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    'b07e222e-ccc0-5cc4-8e49-295a90d56759',
    'Теория вероятностей · Тест 1',
    'Какое из утверждений относительно генеральной и выборочной совокупностей является верным?',
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

INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '92d824d9-c98e-5948-a3c2-c510e5b79c7d',
    'Теория вероятностей · Тест 2',
    'Сумма частот признака равна:',
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

INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '5784ec91-3837-57a7-b6a2-0f2ba6bd1860',
    'Теория вероятностей · Тест 4',
    'Какие из следующих утверждений являются верными?',
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

INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    'a08d1005-3281-5626-973b-eebaf858d4c0',
    'SQL · Тест 1',
    'Необходимо найти пользователей, которые работают на одном предприятии: все они регистрировались в сервисе с почтовых ящиков на корпоративном домене. Какой оператор в SQL-запросе поможет это сделать?',
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

INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    'f58c4649-b2b9-5c4d-a53e-b4226c06ca9c',
    'SQL · Тест 2',
    'Какие операторы в SQL-запросе нужно использовать, чтобы вычислить среднее арифметическое по набору строк?',
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

INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '861ff399-3af4-57a2-a843-3fcf59b34d85',
    'SQL · Тест 3',
    'Какой SQL-запрос выведет все данные о покупках пользователя SuperUser в порядке убывания стоимости товаров:',
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

INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    'a0b5f247-08bb-5ad0-ad57-cb4e0057ab3c',
    'SQL · Тест 4',
    'Какое утверждение верно при проектировании структуры реляционной базы данных?',
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

INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '6e8ba2ff-d6a4-520d-8c09-828a5e851c91',
    'Мат. статистика · Тест 34',
    'Какие из следующих мер являются мерами разброса (изменчивости) данных?',
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

INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '827981d5-cd6c-56ea-bdee-f5889110a798',
    'Pandas · Тест 1',
    'Какой метод Pandas используется для чтения данных из CSV-файла в DataFrame?',
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

INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '3a1a34ab-a068-52b2-9a20-a5665bf4fb77',
    'Pandas · Тест 2',
    'Какие из следующих методов используются для выбора строк в DataFrame Pandas?',
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

INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '3cf3cce1-5f1e-5c41-a410-dda1b0cb6003',
    'Pandas · Тест 3',
    'Какой метод Pandas используется для удаления строк или столбцов из DataFrame?',
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

INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '04ad3022-0bff-5390-a310-7c2c42e503f3',
    'BPMN · Тест 1',
    'Какой элемент BPMN используется для обозначения начала или конца процесса?',
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
    'Поток управления (Sequence Flow)',
    false
);

INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '422e50a4-6f07-5502-b042-6c31d9da1cec',
    'BPMN · Тест 2',
    'Какие из следующих элементов BPMN используются для управления потоком выполнения процесса?',
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

INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '392ca45e-185c-54b5-8c5e-c157ac7c4de7',
    'SQL · Исправление ошибки 1',
    'Найдите и исправьте ошибку в SQL-запросе.',
    'ERROR_SEARCH',
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
ORDER age DESC;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    '985edd6e-e7a4-5d61-ae66-10aa1d1a65da',
    '392ca45e-185c-54b5-8c5e-c157ac7c4de7',
    'SELECT name, age
FROM users
WHERE age > 18 AND city = ''New York''
ORDER BY age DESC;',
    true
);

INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '720febcb-df2e-5188-8a3a-2e9acda910c7',
    'SQL · Открытый вопрос 2',
    'Какое число будет выведено в результате выполнения следующего SQL-запроса, если в таблице orders есть 150 записей?
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

INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    'd53698d9-b1e7-5f5e-99d4-8b06c9b2a3b5',
    'Мат. статистика · Открытый вопрос 1',
    'Вам дан набор данных с 100 наблюдениями. Среднее значение равно 50, а стандартное отклонение равно 10. Если вы увеличите каждое наблюдение на 5, каким будет новое среднее значение?',
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

INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    'cc68303d-0523-586e-9eae-21c178f7ddb1',
    'Мат. статистика · Открытый вопрос 2',
    'Вам дан набор данных из 5 чисел: 10, 12, 15, 18, 20. Каково медианное значение этого набора данных?',
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

INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    '04bea691-cf1f-5102-925a-a472f7aff5a1',
    'Визуализация данных (Matplotlib/Seaborn) · Исправление ошибки 2',
    'Найдите и исправьте ошибку в коде Python с использованием Seaborn для построения гистограммы.',
    'ERROR_SEARCH',
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

sns.histplot(data=df, x=''value'') 
plt.show()',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'c4fdd065-45ab-59ec-adce-bbf588cc9811',
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

INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    'a1b2c3d4-e5f6-7890-abcd-ef1234567890',
    'Pandas · Исправление ошибки 3',
    'Найдите и исправьте ошибку в коде Python для фильтрации данных в DataFrame.',
    'ERROR_SEARCH',
    'Hard',
    'Middle',
    20,
    0
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'b2c3d4e5-f6a7-8901-bcde-f12345678901',
    'a1b2c3d4-e5f6-7890-abcd-ef1234567890',
    'import pandas as pd

df = pd.DataFrame({''name'': [''Anna'', ''Bob'', ''Carol''], ''age'': [25, 30, 35]})

result = df[df[''age''] > 25 and df[''name''].str.startswith(''A'')]
print(result)',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'c3d4e5f6-a7b8-9012-cdef-123456789012',
    'a1b2c3d4-e5f6-7890-abcd-ef1234567890',
    'import pandas as pd

df = pd.DataFrame({''name'': [''Anna'', ''Bob'', ''Carol''], ''age'': [25, 30, 35]})

result = df[(df[''age''] > 25) & (df[''name''].str.startswith(''A''))]
print(result)',
    true
);

INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    'd4e5f6a7-b8c9-0123-def0-234567890123',
    'NumPy · Исправление ошибки 4',
    'Найдите и исправьте ошибку в коде Python для работы с массивами NumPy.',
    'ERROR_SEARCH',
    'Hard',
    'Middle',
    20,
    0
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'e5f6a7b8-c9d0-1234-ef01-345678901234',
    'd4e5f6a7-b8c9-0123-def0-234567890123',
    'import numpy as np

arr = np.array([[1, 2, 3], [4, 5, 6], [7, 8, 9]])

print(arr[1, 2, 0])',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'f6a7b8c9-d0e1-2345-f012-456789012345',
    'd4e5f6a7-b8c9-0123-def0-234567890123',
    'import numpy as np

arr = np.array([[1, 2, 3], [4, 5, 6], [7, 8, 9]])

print(arr[1, 2])',
    true
);

INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    'a7b8c9d0-e1f2-3456-0123-567890123456',
    'Matplotlib · Исправление ошибки 5',
    'Найдите и исправьте ошибку в коде Python для построения диаграммы рассеяния.',
    'ERROR_SEARCH',
    'Hard',
    'Middle',
    20,
    0
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'b8c9d0e1-f2a3-4567-1234-678901234567',
    'a7b8c9d0-e1f2-3456-0123-567890123456',
    'import matplotlib.pyplot as plt

x = [1, 2, 3, 4, 5]
y = [2, 4, 6, 8, 10]

plt.scatter(x, y, color=''blue'', size=100)
plt.xlabel(''X axis'')
plt.ylabel(''Y axis'')
plt.show()',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'c9d0e1f2-a3b4-5678-2345-789012345678',
    'a7b8c9d0-e1f2-3456-0123-567890123456',
    'import matplotlib.pyplot as plt

x = [1, 2, 3, 4, 5]
y = [2, 4, 6, 8, 10]

plt.scatter(x, y, color=''blue'', s=100)
plt.xlabel(''X axis'')
plt.ylabel(''Y axis'')
plt.show()',
    true
);

INSERT INTO tasks (id, title, description, task_type, task_level, grade_level, max_points, correct_answers_count) VALUES (
    'd0e1f2a3-b4c5-6789-3456-890123456789',
    'SQL · Исправление ошибки 6',
    'Найдите и исправьте ошибку в SQL-запросе для объединения таблиц.',
    'ERROR_SEARCH',
    'Hard',
    'Middle',
    20,
    0
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'e1f2a3b4-c5d6-7890-4567-901234567890',
    'd0e1f2a3-b4c5-6789-3456-890123456789',
    'SELECT users.name, orders.product
FROM users
INNER JOIN orders ON users.id = orders.user_id
WHERE users.age > 18
GROUP BY users.id;',
    false
);

INSERT INTO task_options (id, task_id, content, is_correct) VALUES (
    'f2a3b4c5-d6e7-8901-5678-012345678901',
    'd0e1f2a3-b4c5-6789-3456-890123456789',
    'SELECT users.name, orders.product
FROM users
INNER JOIN orders ON users.id = orders.user_id
WHERE users.age > 18;',
    true
);