package com.jtt.javatachteam_hakaton.api;

import com.jtt.javatachteam_hakaton.api.routes.AuthRoutes;
import com.jtt.javatachteam_hakaton.api.routes.HealthRoutes;
import com.jtt.javatachteam_hakaton.api.routes.TaskRoutes;
import com.jtt.javatachteam_hakaton.api.routes.UserRoutes;
import io.javalin.config.RoutesConfig;

public final class ApiRouter {

    private ApiRouter() {
    }

    public static void register(RoutesConfig routes) {
        routes.apiBuilder(() -> {
            HealthRoutes.register();
            AuthRoutes.register();
            UserRoutes.register();
            TaskRoutes.register();
        });
    }
}
