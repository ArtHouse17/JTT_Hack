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
import com.jtt.javatachteam_hakaton.service.AttemptService;
import com.jtt.javatachteam_hakaton.service.AuthService;
import com.jtt.javatachteam_hakaton.service.SqlTaskEvaluationService;
import com.jtt.javatachteam_hakaton.service.TaskService;
import com.jtt.javatachteam_hakaton.service.UserService;
import io.javalin.Javalin;
import io.javalin.http.HttpResponseException;
import jakarta.persistence.EntityManagerFactory;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        SqlTaskConfigRepository sqlTaskConfigRepository = new SqlTaskConfigRepository(entityManagerFactory);

        // --- Инициализация Сервисов ---
        SqlTaskEvaluationService sqlTaskEvaluationService = new SqlTaskEvaluationService(dataSource, sqlTaskConfigRepository);
        AttemptService attemptService = new AttemptService(attemptRepository,
                attemptAnswerRepository, taskRepository, userRepository, taskOptionRepository, sqlTaskEvaluationService);
        TaskService taskService = new TaskService(taskRepository, taskOptionRepository, attemptRepository, sqlTaskEvaluationService);
        AuthService authService = new AuthService(userRepository);
        UserService userService = new UserService(userRepository, attemptRepository, taskRepository);

        // --- Инициализация Контроллеров (Handlers) ---
        TaskHandler taskHandler = new TaskHandler(taskService, attemptService);
        AuthHandler authHandler = new AuthHandler(authService);
        HealthHandler healthHandler = new HealthHandler();
        UserHandler userHandler = new UserHandler(userService);

        Logger appLogger = Logger.getLogger("com.jtt");

        // --- Настройка Javalin и Роутинга ---
          Javalin app = Javalin.create(javalinConfig -> {
            javalinConfig.bundledPlugins.enableCors(cors -> {
                cors.addRule(it -> {
                    it.allowHost(
                        "http://localhost:5173",
                        "http://localhost:4173"
                    );
                    it.allowCredentials = true;
                });
            });
            javalinConfig.router.handlerWrapper(endpoint -> ctx -> {
                try {
                    endpoint.handler.handle(ctx);
                } catch (HttpResponseException e) {
                    throw e;
                } catch (Exception e) {
                    appLogger.log(Level.SEVERE, "Unhandled exception in " + endpoint.path, e);
                    ctx.status(500).json(Map.of("message", "Внутренняя ошибка сервера"));
                }
            });
            ApiRouter.register(javalinConfig, authHandler, taskHandler, healthHandler, userHandler);
        });

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
