package com.jtt.javatachteam_hakaton.config;

import liquibase.Scope;
import liquibase.command.CommandScope;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

import javax.sql.DataSource;
import java.sql.Connection;

public final class LiquibaseMigrator {
    private LiquibaseMigrator() {
    }

    public static void migrate(DataSource dataSource, AppConfig config) {
        try (Connection connection = dataSource.getConnection()) {
            Database database = DatabaseFactory.getInstance()
                .findCorrectDatabaseImplementation(new JdbcConnection(connection));

            Scope.child(
                Scope.Attr.resourceAccessor,
                new ClassLoaderResourceAccessor(),
                () -> new CommandScope("update")
                    .addArgumentValue("database", database)
                    .addArgumentValue("changelogFile", config.liquibaseChangeLog())
                    .execute()
            );
        } catch (Exception exception) {
            throw new IllegalStateException("Failed to run Liquibase migrations", unwrap(exception));
        }
    }

    private static Throwable unwrap(Exception exception) {
        if (exception instanceof LiquibaseException liquibaseException && liquibaseException.getCause() != null) {
            return liquibaseException.getCause();
        }
        return exception;
    }
}
