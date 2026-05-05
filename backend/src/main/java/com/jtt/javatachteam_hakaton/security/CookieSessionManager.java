package com.jtt.javatachteam_hakaton.security;

import java.util.UUID;

public class CookieSessionManager {
    private static final String SESSION_COOKIE_NAME = "session_id";
    private static final int COOKIE_MAX_AGE = 24 * 60 * 60;

    public static String createSessionCookie(UUID userId) {
        String sessionId = UUID.randomUUID().toString();
        SessionStore.createSession(sessionId, userId);
        return sessionId;
    }

    public static UUID getUserIdFromSessionCookie(String sessionId) {
        if (sessionId == null || sessionId.isEmpty()) {
            return null;
        }
        return SessionStore.getUserIdFromSession(sessionId);
    }

    public static void invalidateSession(String sessionId) {
        if (sessionId != null) {
            SessionStore.removeSession(sessionId);
        }
    }

    public static String getSessionCookieName() {
        return SESSION_COOKIE_NAME;
    }

    public static int getCookieMaxAge() {
        return COOKIE_MAX_AGE;
    }
}

