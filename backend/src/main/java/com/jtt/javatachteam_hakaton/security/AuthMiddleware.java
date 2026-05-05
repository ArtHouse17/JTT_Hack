package com.jtt.javatachteam_hakaton.security;

import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class AuthMiddleware {
    private static final Set<String> PUBLIC_PATHS = new HashSet<>(Arrays.asList(
            "/auth/login",
            "/auth/signup",
            "/health"
    ));

    public static void handle(Context ctx) {
        String path = ctx.path();

        if (isPublicPath(path)) {
            return;
        }

        String sessionId = ctx.cookie(CookieSessionManager.getSessionCookieName());
        UUID userId = CookieSessionManager.getUserIdFromSessionCookie(sessionId);

        if (userId == null) {
            ctx.status(HttpStatus.UNAUTHORIZED).json(new ErrorDto("Требуется авторизация"));
            ctx.skipRemainingHandlers();
            return;
        }

        ctx.attribute("userId", userId);
    }

    private static boolean isPublicPath(String path) {
        return PUBLIC_PATHS.stream()
                .anyMatch(publicPath -> path.equals(publicPath) || path.startsWith(publicPath + "/"));
    }

    public static class ErrorDto {
        public String message;

        public ErrorDto(String message) {
            this.message = message;
        }
    }
}

