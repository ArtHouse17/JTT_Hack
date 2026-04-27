package com.jtt.javatachteam_hakaton.api.routes;

import com.jtt.javatachteam_hakaton.api.handlers.UserHandler;

import static io.javalin.apibuilder.ApiBuilder.delete;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;

public final class UserRoutes {
    private UserRoutes() {}

    public static void register(UserHandler handler) {
        path("users", () -> path("me", () -> {
            get(handler::currentUser);
            path("progress", () -> {
                get(handler::currentUserProgress);
                delete(handler::resetCurrentUserProgress);
            });
        }));
    }
}