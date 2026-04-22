package com.jtt.javatachteam_hakaton.api.routes;

import com.jtt.javatachteam_hakaton.api.handlers.HealthHandler;

import static io.javalin.apibuilder.ApiBuilder.get;

public final class HealthRoutes {
    private static final HealthHandler HANDLER = new HealthHandler(RouteDependencies.stubApiService());

    private HealthRoutes() {
    }

    public static void register() {
        get("/health", HANDLER::health);
    }
}
