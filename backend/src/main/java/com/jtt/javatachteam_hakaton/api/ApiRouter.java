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
import io.javalin.Javalin;
import io.javalin.http.HttpStatus;
import io.javalin.http.UnauthorizedResponse;
import io.javalin.http.ForbiddenResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static io.javalin.apibuilder.ApiBuilder.*;

public final class ApiRouter {
    private static final Logger logger = LoggerFactory.getLogger(ApiRouter.class);

    private ApiRouter() {}

    public static void register(
            Javalin app,
            AuthHandler authHandler,
            TaskHandler taskHandler,
            HealthHandler healthHandler,
            UserHandler userHandler,
            AuthMiddleware authMiddleware
    ) {
        // ========== ГЛОБАЛЬНЫЕ ОБРАБОТЧИКИ (вне app.routes) ==========

        // Глобальный before для ВСЕХ запросов
        app.before(ctx -> {
            ctx.header("X-API-Version", "1.0");
            logger.debug("{} {}", ctx.method(), ctx.path());
        });

        // Глобальный after для ВСЕХ запросов
        app.after(ctx -> {
            logger.debug("Response status: {} for {} {}",
                    ctx.status(), ctx.method(), ctx.path());

            if (ctx.result() == null && ctx.path().startsWith("/api")) {
                ctx.status(HttpStatus.NOT_FOUND.getCode());
                ctx.json(Map.of("error", "API endpoint not found"));
            }
        });

        // ========== ГЛОБАЛЬНЫЕ ОБРАБОТЧИКИ ИСКЛЮЧЕНИЙ ==========

        app.exception(UnauthorizedResponse.class, (e, ctx) -> {
            ctx.status(HttpStatus.UNAUTHORIZED.getCode());
            ctx.json(Map.of("error", e.getMessage()));
        });

        app.exception(ForbiddenResponse.class, (e, ctx) -> {
            ctx.status(HttpStatus.FORBIDDEN.getCode());
            ctx.json(Map.of("error", e.getMessage()));
        });

        app.exception(Exception.class, (e, ctx) -> {
            logger.error("Unexpected error: {}", e.getMessage(), e);
            ctx.status(HttpStatus.INTERNAL_SERVER_ERROR.getCode());
            ctx.json(Map.of("error", "Internal server error"));
        });

        // ========== РЕГИСТРАЦИЯ МАРШРУТОВ (через app.routes) ==========

        app.routes(() -> {

            // === Публичные маршруты ===
            AuthRoutes.register(authHandler);
            HealthRoutes.register(healthHandler);

            // === Защищенные маршруты ===
            before("api/*", authMiddleware::requireAuth);

            // === API маршруты с префиксом /api ===
            path("api", () -> {
                TaskRoutes.register(taskHandler);
                UserRoutes.register(userHandler, authMiddleware);
            });
        });
    }
}