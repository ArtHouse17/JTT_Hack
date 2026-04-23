package com.jtt.javatachteam_hakaton.api.routes;

import com.jtt.javatachteam_hakaton.api.handlers.AuthHandler;

import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

public final class AuthRoutes {
    private static final AuthHandler HANDLER = new AuthHandler(RouteDependencies.stubApiService());

    private AuthRoutes() {
    }

    public static void register() {
        path("auth", () -> {
            post("login", HANDLER::login);
            post("logout", HANDLER::logout);
            post("signup", HANDLER::signup);
        });
    }
}
