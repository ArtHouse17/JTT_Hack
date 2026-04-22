package com.jtt.javatachteam_hakaton.api.handlers;

import com.jtt.javatachteam_hakaton.api.service.StubApiService;
import io.javalin.http.Context;

public class UserHandler {
    private final StubApiService stubApiService;

    public UserHandler(StubApiService stubApiService) {
        this.stubApiService = stubApiService;
    }

    public void currentUser(Context ctx) {
        ctx.json(stubApiService.currentUser());
    }

    public void currentUserProgress(Context ctx) {
        ctx.json(stubApiService.currentUserProgress());
    }

    public void resetCurrentUserProgress(Context ctx) {
        ctx.json(stubApiService.resetCurrentUserProgress());
    }
}
