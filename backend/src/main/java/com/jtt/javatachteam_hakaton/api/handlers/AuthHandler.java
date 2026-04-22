package com.jtt.javatachteam_hakaton.api.handlers;

import com.jtt.javatachteam_hakaton.api.service.StubApiService;
import io.javalin.http.Context;

public class AuthHandler {
    private final StubApiService stubApiService;

    public AuthHandler(StubApiService stubApiService) {
        this.stubApiService = stubApiService;
    }

    public void login(Context ctx) {
        ctx.json(stubApiService.login());
    }

    public void logout(Context ctx) {
        ctx.json(stubApiService.logout());
    }

    public void signup(Context ctx) {
        ctx.json(stubApiService.signup());
    }
}
