package com.jtt.javatachteam_hakaton.security;

import com.jtt.javatachteam_hakaton.repository.UserRepository;

public class SecurityConfig {

    private final TokenBlacklistService tokenBlacklistService;
    private final AuthMiddleware authMiddleware;

    public SecurityConfig(TokenBlacklistService tokenBlacklistService, UserRepository userRepository) {
        this.tokenBlacklistService = tokenBlacklistService;
        this.authMiddleware = new AuthMiddleware(tokenBlacklistService, userRepository);
    }

    public TokenBlacklistService getTokenBlacklistService() {
        return tokenBlacklistService;
    }

    public AuthMiddleware getAuthMiddleware() {
        return authMiddleware;
    }
}