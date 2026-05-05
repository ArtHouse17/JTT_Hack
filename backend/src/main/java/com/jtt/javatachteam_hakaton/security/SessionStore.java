package com.jtt.javatachteam_hakaton.security;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SessionStore {
    private static final ConcurrentMap<String, SessionData> sessions = new ConcurrentHashMap<>();
    private static final long SESSION_TIMEOUT = 24 * 60 * 60 * 1000;

    public static void createSession(String sessionId, UUID userId) {
        sessions.put(sessionId, new SessionData(userId, System.currentTimeMillis()));
    }

    public static UUID getUserIdFromSession(String sessionId) {
        SessionData data = sessions.get(sessionId);
        if (data == null) {
            return null;
        }

        if (System.currentTimeMillis() - data.createdAt > SESSION_TIMEOUT) {
            sessions.remove(sessionId);
            return null;
        }

        return data.userId;
    }

    public static void removeSession(String sessionId) {
        sessions.remove(sessionId);
    }

    public static void cleanExpiredSessions() {
        long now = System.currentTimeMillis();
        sessions.entrySet().removeIf(entry -> now - entry.getValue().createdAt > SESSION_TIMEOUT);
    }

    private static class SessionData {
        UUID userId;
        long createdAt;

        SessionData(UUID userId, long createdAt) {
            this.userId = userId;
            this.createdAt = createdAt;
        }
    }
}

