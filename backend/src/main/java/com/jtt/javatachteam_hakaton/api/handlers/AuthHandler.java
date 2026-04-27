package com.jtt.javatachteam_hakaton.api.handlers;

import com.jtt.javatachteam_hakaton.service.AuthService;
import io.javalin.http.Context;

public class AuthHandler {
    private final AuthService authService;

    public AuthHandler(AuthService authService) {
        this.authService = authService;
    }

    public void login(Context ctx) {
        // Парсим тело запроса
        LoginRequest req = ctx.bodyAsClass(LoginRequest.class);

        String token = authService.login(req.username(), req.password());

        if (token != null) {
            ctx.status(200).json(new AuthResponse(token));
        } else {
            ctx.status(401).json(new ErrorResponse("Неверное имя пользователя или пароль"));
        }
    }

    public void signup(Context ctx) {
        SignupRequest req = ctx.bodyAsClass(SignupRequest.class);

        try {
            String token = authService.signup(req.username(), req.password(), req.firstname(), req.lastname());
            ctx.status(201).json(new AuthResponse(token));
        } catch (IllegalArgumentException e) {
            ctx.status(400).json(new ErrorResponse(e.getMessage()));
        }
    }

    public void logout(Context ctx) {
        // Обрабатываем на фронте
        ctx.json(new ErrorResponse("Успешный выход"));
    }

    // --- Внутренние DTO для автоматического парсинга JSON ---
    public record LoginRequest(String username, String password) {}
    public record SignupRequest(String username, String password, String firstname, String lastname) {}
    public record AuthResponse(String token) {}
    public record ErrorResponse(String message) {}
}