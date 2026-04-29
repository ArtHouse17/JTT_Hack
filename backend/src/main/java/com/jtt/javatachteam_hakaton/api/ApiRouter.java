package com.jtt.javatachteam_hakaton.api;

import com.jtt.javatachteam_hakaton.api.handlers.AuthHandler;
import com.jtt.javatachteam_hakaton.api.handlers.HealthHandler;
import com.jtt.javatachteam_hakaton.api.handlers.TaskHandler;
import com.jtt.javatachteam_hakaton.api.handlers.UserHandler;
import com.jtt.javatachteam_hakaton.api.routes.AuthRoutes;
import com.jtt.javatachteam_hakaton.api.routes.HealthRoutes;
import com.jtt.javatachteam_hakaton.api.routes.TaskRoutes;
import com.jtt.javatachteam_hakaton.api.routes.UserRoutes;
import com.jtt.javatachteam_hakaton.security.AuthMiddleware;
import io.javalin.config.JavalinConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

import static io.javalin.apibuilder.ApiBuilder.*;

public final class ApiRouter {
    private static final Logger logger = LoggerFactory.getLogger(ApiRouter.class);

    private ApiRouter() {}

    public static Consumer<JavalinConfig> getRoutes(
            AuthHandler authHandler,
            TaskHandler taskHandler,
            HealthHandler healthHandler,
            UserHandler userHandler,
            AuthMiddleware authMiddleware
    ) {
        return config -> config.routes.apiBuilder(() -> {

            // Глобальный before для всех маршрутов
            before(ctx -> {
                ctx.header("X-API-Version", "1.0");
                logger.debug("{} {}", ctx.method(), ctx.path());
            });

            // === Публичные маршруты ===
            HealthRoutes.register(healthHandler);
            AuthRoutes.register(authHandler);

            // === Защищенные маршруты (требуют аутентификацию) ===
            before("api/*", authMiddleware::requireAuth);

            // === Все API маршруты с префиксом /api ===
            path("api", () -> {
                UserRoutes.register(userHandler, authMiddleware);
                TaskRoutes.register(taskHandler);
            });
        });
    }
}