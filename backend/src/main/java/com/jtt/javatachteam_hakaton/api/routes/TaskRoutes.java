package com.jtt.javatachteam_hakaton.api.routes;

import com.jtt.javatachteam_hakaton.api.handlers.TaskHandler;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

public final class TaskRoutes {
    private static final TaskHandler HANDLER = new TaskHandler(RouteDependencies.stubApiService());

    private TaskRoutes() {
    }

    public static void register() {
        path("tasks", () -> {
            get(HANDLER::tasks);
            path("{taskId}", () -> {
                get(HANDLER::taskById);
                path("attempts", () -> post(HANDLER::submitTaskAttempt));
            });
        });
    }
}
