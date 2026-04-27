package com.jtt.javatachteam_hakaton.api.routes;

import com.jtt.javatachteam_hakaton.api.handlers.HealthHandler;

import static io.javalin.apibuilder.ApiBuilder.get;

public final class HealthRoutes {
    private HealthRoutes() {}

    public static void register(HealthHandler handler) {
        get("/health", handler::health);
    }
}