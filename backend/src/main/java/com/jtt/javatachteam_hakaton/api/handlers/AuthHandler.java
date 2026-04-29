package com.jtt.javatachteam_hakaton.api.handlers;

import com.jtt.javatachteam_hakaton.security.JwtProvider;
import com.jtt.javatachteam_hakaton.security.TokenBlacklistService;
import com.jtt.javatachteam_hakaton.service.AuthService;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class AuthHandler {
    private static final Logger logger = LoggerFactory.getLogger(AuthHandler.class);

    private final AuthService authService;
    private final TokenBlacklistService tokenBlacklistService;

    public AuthHandler(AuthService authService, TokenBlacklistService tokenBlacklistService) {
        this.authService = authService;
        this.tokenBlacklistService = tokenBlacklistService;
    }

    public void login(Context ctx) {
        try {
            LoginRequest req = ctx.bodyAsClass(LoginRequest.class);
            logger.info("Login attempt for user: {}", req.username());

            String token = authService.login(req.username(), req.password());

            if (token != null) {
                logger.info("User {} logged in successfully", req.username());
                ctx.status(200).json(new AuthResponse(token));
            } else {
                logger.warn("Failed login attempt for user: {}", req.username());
                ctx.status(401).json(new ErrorResponse("Неверное имя пользователя или пароль"));
            }
        } catch (Exception e) {
            logger.error("Login error: {}", e.getMessage());
            ctx.status(500).json(new ErrorResponse("Internal server error"));
        }
    }

    public void signup(Context ctx) {
        try {
            SignupRequest req = ctx.bodyAsClass(SignupRequest.class);
            logger.info("Signup attempt for user: {}", req.username());

            String token = authService.signup(req.username(), req.password(),
                    req.firstname(), req.lastname());
            logger.info("User {} registered successfully", req.username());
            ctx.status(201).json(new AuthResponse(token));

        } catch (IllegalArgumentException e) {
            logger.warn("Signup failed for user: {}", e.getMessage());
            ctx.status(400).json(new ErrorResponse(e.getMessage()));
        } catch (Exception e) {
            logger.error("Signup error: {}", e.getMessage());
            ctx.status(500).json(new ErrorResponse("Internal server error"));
        }
    }

    public void logout(Context ctx) {
        try {
            String authHeader = ctx.header("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                ctx.status(400).json(new ErrorResponse("No token provided"));
                return;
            }

            String token = authHeader.substring(7);

            // Добавляем токен в черный список
            tokenBlacklistService.blacklist(token);

            // Логируем выход пользователя
            try {
                UUID userId = JwtProvider.extractUserId(token);
                logger.info("User {} logged out successfully", userId);
            } catch (Exception e) {
                logger.warn("Could not extract user ID from token during logout");
            }

            ctx.status(200).json(new SuccessResponse("Успешный выход из системы"));

        } catch (IllegalArgumentException e) {
            logger.warn("Logout with invalid token: {}", e.getMessage());
            ctx.status(400).json(new ErrorResponse(e.getMessage()));
        } catch (Exception e) {
            logger.error("Logout error: {}", e.getMessage());
            ctx.status(500).json(new ErrorResponse("Internal server error"));
        }
    }

    // --- Внутренние DTO для автоматического парсинга JSON ---
    public record LoginRequest(String username, String password) {}
    public record SignupRequest(String username, String password, String firstname, String lastname) {}
    public record AuthResponse(String token) {}
    public record ErrorResponse(String message) {}
    public record SuccessResponse(String message) {}
}