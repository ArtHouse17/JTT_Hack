package com.jtt.javatachteam_hakaton.api.handlers;

import com.jtt.javatachteam_hakaton.api.service.StubApiService;
import io.javalin.http.Context;

public class TaskHandler {
    private final StubApiService stubApiService;

    public TaskHandler(StubApiService stubApiService) {
        this.stubApiService = stubApiService;
    }

    public void tasks(Context ctx) {
        ctx.json(stubApiService.tasks());
    }

    public void taskById(Context ctx) {
        ctx.json(stubApiService.taskById(ctx.pathParam("taskId")));
    }

    public void submitTaskAttempt(Context ctx) {
        ctx.json(stubApiService.submitTaskAttempt(ctx.pathParam("taskId")));
    }
}
