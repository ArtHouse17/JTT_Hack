package com.jtt.javatachteam_hakaton.api.handlers;

import com.jtt.javatachteam_hakaton.service.AuthService;
import io.javalin.http.Context;
import io.javalin.http.Cookie;

public class AuthHandler {
    private final AuthService authService;
    private static final String TOKEN_COOKIE = "auth_token";
    private static final int COOKIE_MAX_AGE = 86400;

    public AuthHandler(AuthService authService) {
        this.authService = authService;
    }

    public void login(Context ctx) {
        try {
            LoginRequest req = ctx.bodyAsClass(LoginRequest.class);
            String token = authService.login(req.username(), req.password());
            setAuthCookie(ctx, token);
            ctx.status(200).json(new AuthResponse(token));
        } catch (IllegalArgumentException e) {
            ctx.status(401).json(new ErrorResponse(e.getMessage()));
        }
    }

    public void signup(Context ctx) {
        SignupRequest req = ctx.bodyAsClass(SignupRequest.class);

        try {
            String token = authService.signup(req.username(), req.password(), req.firstname(), req.lastname());
            setAuthCookie(ctx, token);
            ctx.status(201).json(new AuthResponse(token));
        } catch (IllegalArgumentException e) {
            ctx.status(400).json(new ErrorResponse(e.getMessage()));
        }
    }

    public void logout(Context ctx) {
        ctx.removeCookie(TOKEN_COOKIE, "/");
        ctx.json(new ErrorResponse("Успешный выход"));
    }

    private void setAuthCookie(Context ctx, String token) {
        Cookie cookie = new Cookie(TOKEN_COOKIE, token);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setMaxAge(COOKIE_MAX_AGE);
        cookie.setPath("/");
        ctx.cookie(cookie);
    }

    // --- Внутренние DTO для автоматического парсинга JSON ---
    public record LoginRequest(String username, String password) {}
    public record SignupRequest(String username, String password, String firstname, String lastname) {}
    public record AuthResponse(String token) {}
    public record ErrorResponse(String message) {}
}