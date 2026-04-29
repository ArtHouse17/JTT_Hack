package com.jtt.javatachteam_hakaton.api.routes;

import com.jtt.javatachteam_hakaton.api.handlers.TaskHandler;
import static io.javalin.apibuilder.ApiBuilder.*;

public final class TaskRoutes {
    private TaskRoutes() {}

    public static void register(TaskHandler handler) {
        path("tasks", () -> {
            get(handler::tasks);
            path("{taskId}", () -> {
                get(handler::taskById);
                path("attempts", () -> post(handler::submitTaskAttempt));
            });
        });
    }
}