package com.jtt.javatachteam_hakaton.api.handlers;

import com.jtt.javatachteam_hakaton.api.service.StubApiService;
import io.javalin.http.Context;

public class HealthHandler {
    private final StubApiService stubApiService;

    public HealthHandler(StubApiService stubApiService) {
        this.stubApiService = stubApiService;
    }

    public void health(Context ctx) {
        ctx.json(stubApiService.health());
    }
}
