package com.jtt.javatachteam_hakaton.api.routes;

import com.jtt.javatachteam_hakaton.api.handlers.HealthHandler;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;

public final class HealthRoutes {
    private HealthRoutes() {}

    public static void register(HealthHandler handler) {
        path("health", () -> {
            get(handler::health);
        });
    }
}