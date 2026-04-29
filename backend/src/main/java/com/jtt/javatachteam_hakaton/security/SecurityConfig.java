package com.jtt.javatachteam_hakaton.security;

import com.jtt.javatachteam_hakaton.repository.UserRepository;
import com.jtt.javatachteam_hakaton.service.TokenBlacklistService;

/**
 * Конфигурация для создания экземпляров security-компонентов
 * Используется для dependency injection
 */
public class SecurityConfig {

    private final TokenBlacklistService tokenBlacklistService;
    private final AuthMiddleware authMiddleware;

    public SecurityConfig(UserRepository userRepository) {
        this.tokenBlacklistService = new TokenBlacklistService();
        this.authMiddleware = new AuthMiddleware(tokenBlacklistService, userRepository);
    }

    public TokenBlacklistService getTokenBlacklistService() {
        return tokenBlacklistService;
    }

    public AuthMiddleware getAuthMiddleware() {
        return authMiddleware;
    }
}