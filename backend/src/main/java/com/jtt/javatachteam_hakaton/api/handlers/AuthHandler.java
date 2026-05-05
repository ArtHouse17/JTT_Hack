package com.jtt.javatachteam_hakaton.api.handlers;

import com.jtt.javatachteam_hakaton.security.CookieSessionManager;
import com.jtt.javatachteam_hakaton.service.AuthService;
import io.javalin.http.Context;

import java.util.UUID;

public class AuthHandler {
    private final AuthService authService;

    public AuthHandler(AuthService authService) {
        this.authService = authService;
    }

    public void login(Context ctx) {
        LoginRequest req = ctx.bodyAsClass(LoginRequest.class);

        UUID userId = authService.loginWithCookie(req.username(), req.password());

        if (userId != null) {
            String sessionId = CookieSessionManager.createSessionCookie(userId);
            ctx.cookie(CookieSessionManager.getSessionCookieName(), sessionId,
                    CookieSessionManager.getCookieMaxAge());
            ctx.status(200).json(new AuthResponse("Успешный вход"));
        } else {
            ctx.status(401).json(new ErrorResponse("Неверное имя пользователя или пароль"));
        }
    }

    public void signup(Context ctx) {
        SignupRequest req = ctx.bodyAsClass(SignupRequest.class);

        try {
            UUID userId = authService.signupWithCookie(req.username(), req.password(), req.firstname(), req.lastname());
            String sessionId = CookieSessionManager.createSessionCookie(userId);
            ctx.cookie(CookieSessionManager.getSessionCookieName(), sessionId,
                    CookieSessionManager.getCookieMaxAge());
            ctx.status(201).json(new AuthResponse("Успешная регистрация"));
        } catch (IllegalArgumentException e) {
            ctx.status(400).json(new ErrorResponse(e.getMessage()));
        }
    }

    public void logout(Context ctx) {
        String sessionId = ctx.cookie(CookieSessionManager.getSessionCookieName());
        if (sessionId != null) {
            CookieSessionManager.invalidateSession(sessionId);
            ctx.removeCookie(CookieSessionManager.getSessionCookieName());
        }
        ctx.status(200).json(new AuthResponse("Успешный выход"));
    }

    public record LoginRequest(String username, String password) {}
    public record SignupRequest(String username, String password, String firstname, String lastname) {}
    public record AuthResponse(String message) {}
    public record ErrorResponse(String message) {}
}