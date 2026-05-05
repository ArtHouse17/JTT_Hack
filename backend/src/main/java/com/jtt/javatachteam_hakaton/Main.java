package com.jtt.javatachteam_hakaton;

import com.jtt.javatachteam_hakaton.api.ApiRouter;
import com.jtt.javatachteam_hakaton.api.handlers.AuthHandler;
import com.jtt.javatachteam_hakaton.api.handlers.HealthHandler;
import com.jtt.javatachteam_hakaton.api.handlers.TaskHandler;
import com.jtt.javatachteam_hakaton.api.handlers.UserHandler;
import com.jtt.javatachteam_hakaton.config.AppConfig;
import com.jtt.javatachteam_hakaton.config.DataSourceFactory;
import com.jtt.javatachteam_hakaton.config.EntityManagerFactoryProvider;
import com.jtt.javatachteam_hakaton.config.LiquibaseMigrator;
import com.jtt.javatachteam_hakaton.repository.*;
import com.jtt.javatachteam_hakaton.security.AuthMiddleware;
import com.jtt.javatachteam_hakaton.service.AttemptService;
import com.jtt.javatachteam_hakaton.service.AuthService;
import com.jtt.javatachteam_hakaton.service.TaskService;
import com.jtt.javatachteam_hakaton.service.UserService;
import io.javalin.Javalin;
import jakarta.persistence.EntityManagerFactory;

import javax.sql.DataSource;

public final class Main {

    public static void main(String[] args) {
        AppConfig config = AppConfig.fromEnvironment();
        DataSource dataSource = DataSourceFactory.create(config);
        LiquibaseMigrator.migrate(dataSource, config);
        EntityManagerFactory entityManagerFactory = EntityManagerFactoryProvider.create(dataSource);

        // --- Инициализация Репозиториев ---
        AttemptRepository attemptRepository = new AttemptRepository(entityManagerFactory);
        TaskOptionRepository taskOptionRepository = new TaskOptionRepository(entityManagerFactory);
        TaskRepository taskRepository = new TaskRepository(entityManagerFactory);
        AttemptAnswerRepository attemptAnswerRepository = new AttemptAnswerRepository(entityManagerFactory);
        UserRepository userRepository = new UserRepository(entityManagerFactory);

        // --- Инициализация Сервисов ---
        AttemptService attemptService = new AttemptService(attemptRepository,
                attemptAnswerRepository, taskRepository, userRepository, taskOptionRepository);
        TaskService taskService = new TaskService(taskRepository, taskOptionRepository, attemptRepository);
        AuthService authService = new AuthService(userRepository);
        UserService userService = new UserService(userRepository, attemptRepository);

        // --- Инициализация Контроллеров (Handlers) ---
        TaskHandler taskHandler = new TaskHandler(taskService, attemptService);
        AuthHandler authHandler = new AuthHandler(authService);
        HealthHandler healthHandler = new HealthHandler();
        UserHandler userHandler = new UserHandler(userService);

        // --- Настройка Javalin и Роутинга ---
        Javalin app = Javalin.create(javalinConfig -> {
            ApiRouter.register(javalinConfig, authHandler, taskHandler, healthHandler, userHandler);
        });

        app.before(AuthMiddleware::handle);

        // --- Graceful Shutdown ---
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
