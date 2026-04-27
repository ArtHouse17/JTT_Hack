package com.jtt.javatachteam_hakaton.api;

import com.jtt.javatachteam_hakaton.api.handlers.AuthHandler;
import com.jtt.javatachteam_hakaton.api.handlers.HealthHandler;
import com.jtt.javatachteam_hakaton.api.handlers.TaskHandler;
import com.jtt.javatachteam_hakaton.api.handlers.UserHandler;
import com.jtt.javatachteam_hakaton.api.routes.AuthRoutes;
import com.jtt.javatachteam_hakaton.api.routes.HealthRoutes;
import com.jtt.javatachteam_hakaton.api.routes.TaskRoutes;
import com.jtt.javatachteam_hakaton.api.routes.UserRoutes;
import io.javalin.config.JavalinConfig;

public final class ApiRouter {
    private ApiRouter() {}

    public static void register(JavalinConfig config, AuthHandler authHandler, TaskHandler taskHandler, HealthHandler healthHandler, UserHandler userHandler) {
        config.routes.apiBuilder(() -> {
            AuthRoutes.register(authHandler);
            TaskRoutes.register(taskHandler);
            HealthRoutes.register(healthHandler);
            UserRoutes.register(userHandler);
        });
    }
}