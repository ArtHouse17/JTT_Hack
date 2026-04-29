package com.jtt.javatachteam_hakaton.api.routes;

import com.jtt.javatachteam_hakaton.api.handlers.AuthHandler;
import static io.javalin.apibuilder.ApiBuilder.*;

public final class AuthRoutes {
    private AuthRoutes() {}

    public static void register(AuthHandler handler) {
        path("auth", () -> {
            post("login", handler::login);
            post("logout", handler::logout);
            post("signup", handler::signup);
        });
    }
}