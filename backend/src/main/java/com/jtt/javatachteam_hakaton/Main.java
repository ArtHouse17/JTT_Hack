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
import com.jtt.javatachteam_hakaton.security.TokenBlacklistService;
import com.jtt.javatachteam_hakaton.service.AttemptService;
import com.jtt.javatachteam_hakaton.service.AuthService;
import com.jtt.javatachteam_hakaton.service.TaskService;
import com.jtt.javatachteam_hakaton.service.UserService;
import io.javalin.Javalin;
import jakarta.persistence.EntityManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.sql.DataSource;

public final class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Запуск приложения Javalin...");

        // --- Загрузка конфигурации ---
        AppConfig config = AppConfig.fromEnvironment();
        logger.info("Конфигурация загружена. Порт сервера: {}", config.serverPort());

        // --- Настройка базы данных ---
        DataSource dataSource = DataSourceFactory.create(config);
        LiquibaseMigrator.migrate(dataSource, config);
        EntityManagerFactory entityManagerFactory = EntityManagerFactoryProvider.create(dataSource);
        logger.info("База данных успешно инициализирована");

        // --- Инициализация репозиториев ---
        AttemptRepository attemptRepository = new AttemptRepository(entityManagerFactory);
        TaskOptionRepository taskOptionRepository = new TaskOptionRepository(entityManagerFactory);
        TaskRepository taskRepository = new TaskRepository(entityManagerFactory);
        AttemptAnswerRepository attemptAnswerRepository = new AttemptAnswerRepository(entityManagerFactory);
        UserRepository userRepository = new UserRepository(entityManagerFactory);

        // --- Инициализация сервисов ---
        AttemptService attemptService = new AttemptService(attemptRepository,
                attemptAnswerRepository, taskRepository, userRepository, taskOptionRepository);
        TaskService taskService = new TaskService(taskRepository, taskOptionRepository, attemptRepository);
        AuthService authService = new AuthService(userRepository);
        UserService userService = new UserService(userRepository, attemptRepository);

        // --- Настройка безопасности ---
        TokenBlacklistService tokenBlacklistService = new TokenBlacklistService();
        AuthMiddleware authMiddleware = new AuthMiddleware(tokenBlacklistService, userRepository);

        // --- Инициализация хендлеров ---
        TaskHandler taskHandler = new TaskHandler(taskService, attemptService);
        AuthHandler authHandler = new AuthHandler(authService, tokenBlacklistService);
        HealthHandler healthHandler = new HealthHandler();
        UserHandler userHandler = new UserHandler(userService);

        // --- Создание Javalin приложения с регистрацией маршрутов ---
        Javalin app = Javalin.create(javalinConfig ->
            ApiRouter.getRoutes(authHandler, taskHandler, healthHandler, userHandler, authMiddleware)
                    .accept(javalinConfig)
        );


        // --- Graceful Shutdown ---
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Закрытие приложения...");
            app.stop();
            entityManagerFactory.close();
            if (dataSource instanceof AutoCloseable autoCloseable) {
                try {
                    autoCloseable.close();
                    logger.info("Источник данных успешно закрыт");
                } catch (Exception e) {
                    logger.error("Ошибка при закрытии источника данных: {}", e.getMessage());
                }
            }
            logger.info("Закрытие приложения завершено");
        }));

        // Запуск сервера
        app.start(config.serverPort());
        logger.info("Сервер запущен на порту {}", config.serverPort());
    }
}
