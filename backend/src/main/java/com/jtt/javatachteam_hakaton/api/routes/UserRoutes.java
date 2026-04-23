package com.jtt.javatachteam_hakaton.api.routes;

import com.jtt.javatachteam_hakaton.api.handlers.UserHandler;

import static io.javalin.apibuilder.ApiBuilder.delete;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;

public final class UserRoutes {
    private static final UserHandler HANDLER = new UserHandler(RouteDependencies.stubApiService());

    private UserRoutes() {
    }

    public static void register() {
        path("users", () -> path("me", () -> {
            get(HANDLER::currentUser);
            path("progress", () -> {
                get(HANDLER::currentUserProgress);
                delete(HANDLER::resetCurrentUserProgress);
            });
        }));
    }
}
