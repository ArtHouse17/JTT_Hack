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
import io.javalin.http.UnauthorizedResponse;
import io.javalin.http.ForbiddenResponse;

import static io.javalin.apibuilder.ApiBuilder.*;

public final class ApiRouter {
    private ApiRouter() {}

    public static void register(
            JavalinConfig config,
            AuthHandler authHandler,
            TaskHandler taskHandler,
            HealthHandler healthHandler,
            UserHandler userHandler,
            AuthMiddleware authMiddleware
    ) {
        config.routes.apiBuilder(() -> {

            // Глобальный before для всех API (логирование, CORS и т.д.)
            before(ctx -> {
                ctx.header("X-API-Version", "1.0");
                // Логирование запросов
                System.out.println("[" + System.currentTimeMillis() + "] " +
                        ctx.method() + " " + ctx.path());
            });

            // === Публичные маршруты (без аутентификации) ===
            AuthRoutes.register(authHandler);
            HealthRoutes.register(healthHandler);

            // === Защищенные маршруты (требуют аутентификации) ===
            before("/*", authMiddleware::requireAuth);

            // Маршруты для заданий
            TaskRoutes.register(taskHandler);

            // Маршруты для пользователей
            UserRoutes.register(userHandler, authMiddleware);

            // === Глобальный обработчик 404 для API ===
            after(ctx -> {
                if (ctx.result() == null && ctx.path().startsWith("/api")) {
                    ctx.status(404).json(Map.of("error", "API endpoint not found"));
                }
            });
        });

        // Настройка обработчиков исключений
        config.routes.exception(UnauthorizedResponse.class, (e, ctx) -> {
            ctx.status(401).json(Map.of("error", e.getMessage()));
        });

        config.routes.exception(ForbiddenResponse.class, (e, ctx) -> {
            ctx.status(403).json(Map.of("error", e.getMessage()));
        });

        config.routes.exception(Exception.class, (e, ctx) -> {
            ctx.status(500).json(Map.of("error", "Internal server error"));
            e.printStackTrace(); // В продакшене использовать логгер
        });
    }
}