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
import com.jtt.javatachteam_hakaton.security.SecurityConfig;
import com.jtt.javatachteam_hakaton.service.AttemptService;
import com.jtt.javatachteam_hakaton.service.AuthService;
import com.jtt.javatachteam_hakaton.service.UserService;
import io.javalin.Javalin;
import jakarta.persistence.EntityManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

public final class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Starting Javalin application...");

        // --- Загрузка конфигурации ---
        AppConfig config = AppConfig.fromEnvironment();
        logger.info("Configuration loaded. Server port: {}", config.serverPort());

        // --- Настройка базы данных ---
        DataSource dataSource = DataSourceFactory.create(config);
        LiquibaseMigrator.migrate(dataSource, config);
        EntityManagerFactory entityManagerFactory = EntityManagerFactoryProvider.create(dataSource);
        logger.info("Database initialized successfully");

        // --- Инициализация репозиториев ---
        AttemptRepository attemptRepository = new AttemptRepository(entityManagerFactory);
        TaskOptionRepository taskOptionRepository = new TaskOptionRepository(entityManagerFactory);
        TaskRepository taskRepository = new TaskRepository(entityManagerFactory);
        AttemptAnswerRepository attemptAnswerRepository = new AttemptAnswerRepository(entityManagerFactory);
        UserRepository userRepository = new UserRepository(entityManagerFactory);

        // --- Инициализация сервисов ---
        AttemptService attemptService = new AttemptService(attemptRepository,
                attemptAnswerRepository, taskRepository, userRepository, taskOptionRepository);
        AuthService authService = new AuthService(userRepository);
        UserService userService = new UserService(userRepository, attemptRepository);

        // --- Настройка безопасности ---
        SecurityConfig securityConfig = new SecurityConfig(userRepository);

        // --- Инициализация хендлеров ---
        TaskHandler taskHandler = new TaskHandler(attemptService);
        AuthHandler authHandler = new AuthHandler(authService, securityConfig.getTokenBlacklistService());
        HealthHandler healthHandler = new HealthHandler();
        UserHandler userHandler = new UserHandler(userService);

        // --- Настройка и запуск Javalin ---
        Javalin app = Javalin.create(javalinConfig -> {
            ApiRouter.register(javalinConfig, authHandler, taskHandler,
                    healthHandler, userHandler, securityConfig.getAuthMiddleware());

            // Дополнительные настройки Javalin
            javalinConfig.showJavalinBanner = true;
            javalinConfig.asyncTimeout = 10_000L;
            javalinConfig.maxRequestSize = 10_485_760L; // 10 MB
        });

        // --- Graceful Shutdown ---
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Shutting down application...");
            app.stop();
            entityManagerFactory.close();
            if (dataSource instanceof AutoCloseable autoCloseable) {
                try {
                    autoCloseable.close();
                    logger.info("DataSource closed successfully");
                } catch (Exception e) {
                    logger.error("Error closing DataSource: {}", e.getMessage());
                }
            }
            logger.info("Application shutdown complete");
        }));

        // Запуск сервера
        app.start(config.serverPort());
        logger.info("Server started on port {}", config.serverPort());
    }
}