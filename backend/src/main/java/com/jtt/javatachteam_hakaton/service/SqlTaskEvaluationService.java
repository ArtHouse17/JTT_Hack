package com.jtt.javatachteam_hakaton.service;

import com.jtt.javatachteam_hakaton.entity.SqlTaskConfig;
import com.jtt.javatachteam_hakaton.repository.SqlTaskConfigRepository;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

public class SqlTaskEvaluationService {
    private static final int QUERY_TIMEOUT_SECONDS = 5;

    private final DataSource dataSource;
    private final SqlTaskConfigRepository sqlTaskConfigRepository;

    public SqlTaskEvaluationService(DataSource dataSource, SqlTaskConfigRepository sqlTaskConfigRepository) {
        this.dataSource = dataSource;
        this.sqlTaskConfigRepository = sqlTaskConfigRepository;
    }

    public boolean isConfigured(UUID taskId) {
        return sqlTaskConfigRepository.findByTaskId(taskId).isPresent();
    }

    public String getStarterSql(UUID taskId) {
        return sqlTaskConfigRepository.findByTaskId(taskId)
            .map(SqlTaskConfig::getStarterSql)
            .orElse("");
    }

    public SqlTaskEvaluationResult evaluate(UUID taskId, String userSql) {
        SqlTaskConfig config = sqlTaskConfigRepository.findByTaskId(taskId)
            .orElseThrow(() -> new IllegalStateException("SQL-конфигурация для задания не найдена"));

        String validationError = validateSelectQuery(userSql);
        if (validationError != null) {
            return SqlTaskEvaluationResult.error("FORBIDDEN_QUERY", validationError);
        }

        try (Connection connection = dataSource.getConnection()) {
            connection.setReadOnly(true);
            connection.setAutoCommit(false);

            try (Statement statement = connection.createStatement()) {
                statement.execute("SET TRANSACTION READ ONLY");
                statement.execute("SET LOCAL search_path TO sql_trainer, public");
                statement.execute("SET LOCAL statement_timeout TO '5s'");
            }

            QueryResult userResult = executeQuery(connection, userSql);
            QueryResult expectedResult = executeQuery(connection, config.getExpectedSql());
            connection.rollback();
            return userResult.equals(expectedResult)
                ? SqlTaskEvaluationResult.success()
                : SqlTaskEvaluationResult.incorrect();
        } catch (SQLException exception) {
            return mapSqlException(exception);
        }
    }

    private QueryResult executeQuery(Connection connection, String sql) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setQueryTimeout(QUERY_TIMEOUT_SECONDS);
            try (ResultSet resultSet = statement.executeQuery()) {
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                List<String> columns = new ArrayList<>(columnCount);
                for (int index = 1; index <= columnCount; index++) {
                    columns.add(metaData.getColumnLabel(index).toLowerCase(Locale.ROOT));
                }

                List<List<String>> rows = new ArrayList<>();
                while (resultSet.next()) {
                    List<String> row = new ArrayList<>(columnCount);
                    for (int index = 1; index <= columnCount; index++) {
                        row.add(normalizeValue(resultSet.getObject(index)));
                    }
                    rows.add(row);
                }

                rows.sort(Comparator.comparing(row -> String.join("\u0001", row)));
                return new QueryResult(columns, rows);
            }
        }
    }

    private String validateSelectQuery(String sql) {
        if (sql == null) {
            return "Пустой SQL-запрос";
        }

        String normalized = sql
            .replaceAll("(?s)/\\*.*?\\*/", " ")
            .replaceAll("(?m)--.*?$", " ")
            .trim();

        if (normalized.isEmpty()) {
            return "Пустой SQL-запрос";
        }

        String collapsed = normalized.replaceAll("\\s+", " ").toLowerCase(Locale.ROOT);
        if (!(collapsed.startsWith("select") || collapsed.startsWith("with"))) {
            return "Разрешены только SELECT и WITH запросы";
        }

        String sanitized = collapsed.replace(";", " ; ");
        String[] forbiddenTokens = {
            "insert", "update", "delete", "drop", "alter", "truncate",
            "create", "grant", "revoke", "comment", "merge", "call",
            "refresh", "vacuum", "analyze"
        };

        for (String token : forbiddenTokens) {
            if (sanitized.matches(".*\\b" + token + "\\b.*")) {
                return "Запрос содержит запрещенную операцию: " + token.toUpperCase(Locale.ROOT);
            }
        }

        boolean hasValidTerminator = normalized.chars().filter(ch -> ch == ';').count() <= 1
            && (!normalized.contains(";") || normalized.endsWith(";"));
        if (!hasValidTerminator) {
            return "Разрешен только один SQL-запрос";
        }

        return null;
    }

    private SqlTaskEvaluationResult mapSqlException(SQLException exception) {
        String sqlState = exception.getSQLState();
        if (sqlState == null || sqlState.isBlank()) {
            return SqlTaskEvaluationResult.error("SQL_ERROR", "Не удалось выполнить SQL-запрос");
        }

        return switch (sqlState) {
            case "42601" -> SqlTaskEvaluationResult.error(sqlState, "Синтаксическая ошибка в SQL-запросе");
            case "42P01" -> SqlTaskEvaluationResult.error(sqlState, "Таблица или представление не найдено");
            case "42703" -> SqlTaskEvaluationResult.error(sqlState, "Указана несуществующая колонка");
            case "42883" -> SqlTaskEvaluationResult.error(sqlState, "Функция вызвана с неверными аргументами");
            case "57014" -> SqlTaskEvaluationResult.error(sqlState, "Превышено время выполнения запроса");
            case "42501" -> SqlTaskEvaluationResult.error(sqlState, "Недостаточно прав для выполнения запроса");
            default -> SqlTaskEvaluationResult.error(sqlState, "Ошибка выполнения SQL-запроса");
        };
    }

    private String normalizeValue(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof String stringValue) {
            return stringValue.trim();
        }
        if (value instanceof BigDecimal decimalValue) {
            return decimalValue.stripTrailingZeros().toPlainString();
        }
        if (value instanceof Timestamp timestampValue) {
            return timestampValue.toInstant().toString();
        }
        if (value instanceof Instant instantValue) {
            return instantValue.toString();
        }
        return Objects.toString(value);
    }

    private record QueryResult(List<String> columns, List<List<String>> rows) {
    }
}
