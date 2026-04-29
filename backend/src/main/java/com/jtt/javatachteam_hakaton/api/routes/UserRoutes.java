package com.jtt.javatachteam_hakaton.api.routes;

import com.jtt.javatachteam_hakaton.api.handlers.UserHandler;
import com.jtt.javatachteam_hakaton.security.AuthMiddleware;

import static io.javalin.apibuilder.ApiBuilder.*;

public final class UserRoutes {
    private UserRoutes() {}

    public static void register(UserHandler handler, AuthMiddleware authMiddleware) {
        path("users", () -> {
            path("me", () -> {
                get(handler::currentUser);
                path("progress", () -> {
                    get(handler::currentUserProgress);
                    delete(handler::resetCurrentUserProgress);
                });
            });

            // Только ADMIN имеет доступ к конкретному пользователю
            path("{id}", () -> {
                before(authMiddleware.requireRole("ADMIN"));
                get(handler::getUserById);
                put(handler::updateUser);
            });
        });
    }
}