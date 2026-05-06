package com.jtt.javatachteam_hakaton.api;

import com.jtt.javatachteam_hakaton.config.JwtProvider;
import io.javalin.http.Context;
import io.javalin.http.UnauthorizedResponse;

import java.util.Optional;
import java.util.UUID;

public final class AuthUtils {
    private static final String TOKEN_COOKIE = "auth_token";
    private static final String BEARER_PREFIX = "Bearer ";

    private AuthUtils() {}

    public static Optional<UUID> extractUserId(Context ctx) {
        String token = extractToken(ctx);
        if (token == null) {
            return Optional.empty();
        }

        try {
            return Optional.of(JwtProvider.extractUserId(token));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static UUID requireUserId(Context ctx) {
        String token = extractToken(ctx);
        if (token == null) {
            throw new UnauthorizedResponse("Необходима авторизация");
        }

        try {
            return JwtProvider.extractUserId(token);
        } catch (Exception e) {
            throw new UnauthorizedResponse("Невалидный или просроченный токен");
        }
    }

    private static String extractToken(Context ctx) {
        String authHeader = ctx.header("Authorization");
        if (authHeader != null && authHeader.startsWith(BEARER_PREFIX)) {
            return authHeader.substring(BEARER_PREFIX.length());
        }

        return ctx.cookie(TOKEN_COOKIE);
    }
}
