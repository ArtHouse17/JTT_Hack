package com.jtt.javatachteam_hakaton;

import com.jtt.javatachteam_hakaton.api.ApiRouter;
import com.jtt.javatachteam_hakaton.config.AppConfig;
import com.jtt.javatachteam_hakaton.config.DataSourceFactory;
import com.jtt.javatachteam_hakaton.config.EntityManagerFactoryProvider;
import com.jtt.javatachteam_hakaton.config.LiquibaseMigrator;
import io.javalin.Javalin;
import jakarta.persistence.EntityManagerFactory;

import javax.sql.DataSource;

public final class Main {

    public static void main(String[] args) {
        AppConfig config = AppConfig.fromEnvironment();
        DataSource dataSource = DataSourceFactory.create(config);
        LiquibaseMigrator.migrate(dataSource, config);
        EntityManagerFactory entityManagerFactory = EntityManagerFactoryProvider.create(dataSource);

        Javalin app = Javalin.create(javalinConfig -> ApiRouter.register(javalinConfig.routes));

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            app.stop();
            entityManagerFactory.close();
            if (dataSource instanceof AutoCloseable autoCloseable) {
                try {
                    autoCloseable.close();
                } catch (Exception ignored) {
                }
            }
        }));
        app.start(config.serverPort());
    }
}
