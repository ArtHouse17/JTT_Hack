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
<<<<<<< Updated upstream
            read("DB_URL", "jdbc:postgresql://postgres:5432/postgres"),
            read("DB_USER", "user"),
            read("DB_PASSWORD", "password"),
            read("DB_DRIVER", "org.postgresql.Driver"),
            read("LIQUIBASE_CHANGELOG", "db/changelog/db.changelog-master.xml"),
            Integer.parseInt(read("PORT", "8080"))
=======
                read("DB_URL", "jdbc:postgresql://localhost:5432/postgres"),
                read("DB_USER", "user"),
                read("DB_PASSWORD", "password"),
                read("DB_DRIVER", "org.postgresql.Driver"),
                read("LIQUIBASE_CHANGELOG", "db/changelog/db.changelog-master.xml"),
                read("JWT_SECRET", "JavaTachTeamHackathonSuperSecretKeyForJwt2026"), // Добавили чтение секрета
                Integer.parseInt(read("PORT", "8080"))
>>>>>>> Stashed changes
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