-- liquibase formatted sql

-- changeset codex:3-add-sql-trainer-support
CREATE SCHEMA IF NOT EXISTS sql_trainer_data;
CREATE SCHEMA IF NOT EXISTS sql_trainer;

CREATE TABLE sql_task_configs (
    task_id UUID PRIMARY KEY REFERENCES tasks(id) ON DELETE CASCADE,
    starter_sql TEXT NOT NULL DEFAULT '',
    expected_sql TEXT NOT NULL
);

CREATE TABLE sql_trainer_data.users_source (
    user_id BIGINT PRIMARY KEY,
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE sql_trainer_data.payments_source (
    payment_id BIGINT PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES sql_trainer_data.users_source(user_id),
    created_at TIMESTAMP NOT NULL,
    amount NUMERIC(12, 2) NOT NULL
);

CREATE OR REPLACE VIEW sql_trainer.users AS
SELECT
    user_id,
    created_at
FROM sql_trainer_data.users_source;

CREATE OR REPLACE VIEW sql_trainer.payments AS
SELECT
    payment_id,
    user_id,
    created_at,
    amount
FROM sql_trainer_data.payments_source;

INSERT INTO sql_trainer_data.users_source (user_id, created_at) VALUES
    (1, '2024-01-10 00:00:00'),
    (2, '2024-01-20 00:00:00'),
    (3, '2024-02-05 00:00:00'),
    (4, '2024-02-17 00:00:00');

INSERT INTO sql_trainer_data.payments_source (payment_id, user_id, created_at, amount) VALUES
    (1, 1, '2024-01-15 00:00:00', 100.00),
    (2, 1, '2024-02-10 00:00:00', 150.00),
    (3, 2, '2024-01-25 00:00:00', 80.00),
    (4, 2, '2024-03-01 00:00:00', 120.00),
    (5, 3, '2024-02-11 00:00:00', 200.00),
    (6, 3, '2024-03-03 00:00:00', 100.00),
    (7, 4, '2024-02-20 00:00:00', 90.00);

INSERT INTO sql_task_configs (task_id, starter_sql, expected_sql) VALUES
(
    'cccc1111-1111-1111-1111-111111111111',
    'WITH cohorts AS (
    SELECT
        user_id,
        DATE_TRUNC(''month'', created_at) AS cohort_month
    FROM users
)
SELECT
    cohort_month,
    COUNT(*) AS users_count
FROM cohorts
GROUP BY cohort_month
ORDER BY cohort_month;',
    'WITH user_cohorts AS (
        SELECT
            user_id,
            DATE_TRUNC(''month'', created_at) AS cohort_month
        FROM users
    ),
    user_ltv AS (
        SELECT
            uc.cohort_month,
            SUM(p.amount) AS total_revenue,
            COUNT(DISTINCT uc.user_id) AS total_users
        FROM user_cohorts uc
        LEFT JOIN payments p ON p.user_id = uc.user_id
        GROUP BY uc.cohort_month
    )
    SELECT
        cohort_month,
        ROUND(total_revenue / NULLIF(total_users, 0), 2) AS ltv
    FROM user_ltv
    ORDER BY cohort_month'
);
