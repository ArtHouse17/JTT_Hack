package com.jtt.javatachteam_hakaton.config;

public record AppConfig(
        String datasourceUrl,
        String datasourceUsername,
        String datasourcePassword,
        String datasourceDriver,
        String liquibaseChangeLog,
        String SECRET,
        int serverPort
) {
    public static AppConfig fromEnvironment() {
        return new AppConfig(
                // Оставляем localhost для запуска из IDEA.
                // Тимлид поменял на postgres, так как готовил это для запуска самого бэкенда внутри Docker.
                read("DB_URL", "jdbc:postgresql://localhost:5432/postgres"),
                read("DB_USER", "user"),
                read("DB_PASSWORD", "password"),
                read("DB_DRIVER", "org.postgresql.Driver"),
                read("LIQUIBASE_CHANGELOG", "db/changelog/db.changelog-master.xml"),
                read("JWT_SECRET", "JavaTachTeamHackathonSuperSecretKeyForJwt2026"),
                Integer.parseInt(read("PORT", "8080"))
        );
    }

    private static String read(String key, String defaultValue) {
        String value = System.getenv(key);
        if (value == null || value.isBlank()) {
            return defaultValue;
        }
        return value;
    }
}