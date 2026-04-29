package com.jtt.javatachteam_hakaton.security;

import com.jtt.javatachteam_hakaton.entity.User;
import com.jtt.javatachteam_hakaton.repository.UserRepository;
import io.javalin.http.Context;
import io.javalin.http.UnauthorizedResponse;
import io.javalin.http.ForbiddenResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.UUID;
import java.util.function.Function;

public class AuthMiddleware {
    private static final Logger logger = LoggerFactory.getLogger(AuthMiddleware.class);

    private final TokenBlacklistService tokenBlacklistService;
    private final UserRepository userRepository;

    public AuthMiddleware(TokenBlacklistService tokenBlacklistService,
                          UserRepository userRepository) {
        this.tokenBlacklistService = tokenBlacklistService;
        this.userRepository = userRepository;
    }

    public void requireAuth(Context ctx) {
        String token = extractToken(ctx);

        if (token == null) {
            logger.warn("Missing authorization token for request: {} {}",
                    ctx.method(), ctx.path());
            throw new UnauthorizedResponse("Missing authorization token");
        }

        try {
            if (tokenBlacklistService.isBlacklisted(token)) {
                logger.warn("Blacklisted token used for request: {} {}",
                        ctx.method(), ctx.path());
                throw new UnauthorizedResponse("Token has been invalidated. Please login again.");
            }

            UUID userId = JwtProvider.extractUserId(token);

            if (JwtProvider.isExpired(token)) {
                logger.warn("Expired token used by user: {}", userId);
                throw new UnauthorizedResponse("Token has expired");
            }

            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new UnauthorizedResponse("User not found"));

            ctx.attribute("userId", userId);
            ctx.attribute("user", user);
            ctx.attribute("roles", user.getRole());
            ctx.attribute("token", token);

            logger.debug("User {} authenticated successfully for request: {} {}",
                    userId, ctx.method(), ctx.path());

        } catch (ExpiredJwtException e) {
            logger.warn("Expired JWT token: {}", e.getMessage());
            throw new UnauthorizedResponse("Token has expired. Please login again.");
        } catch (MalformedJwtException e) {
            logger.warn("Malformed JWT token: {}", e.getMessage());
            throw new UnauthorizedResponse("Invalid token format");
        } catch (SignatureException e) {
            logger.warn("Invalid JWT signature: {}", e.getMessage());
            throw new UnauthorizedResponse("Invalid token signature");
        } catch (Exception e) {
            logger.error("Unexpected error during authentication: {}", e.getMessage());
            throw new UnauthorizedResponse("Authentication failed");
        }
    }

    public void optionalAuth(Context ctx) {
        try {
            String token = extractToken(ctx);
            if (token != null && !tokenBlacklistService.isBlacklisted(token) && JwtProvider.isValidToken(token)) {
                UUID userId = JwtProvider.extractUserId(token);
                User user = userRepository.findById(userId).orElse(null);

                if (user != null) {
                    ctx.attribute("userId", userId);
                    ctx.attribute("user", user);
                    ctx.attribute("roles", user.getRole());
                }
            }
        } catch (Exception e) {
            logger.debug("Optional authentication failed: {}", e.getMessage());
        }
    }

    public io.javalin.http.Handler requireRole(String... allowedRoles) {
        Set<String> allowedRoleSet = Set.of(allowedRoles);

        return ctx -> {
            String userRole = ctx.attribute("roles");

            if (userRole == null) {
                logger.warn("No role found for user, denying access");
                throw new ForbiddenResponse("Access denied: no role assigned");
            }

            if (!allowedRoleSet.contains(userRole.toUpperCase())) {
                logger.warn("User with role {} attempted to access resource requiring roles: {}",
                        userRole, String.join(", ", allowedRoles));
                throw new ForbiddenResponse("Access denied: insufficient permissions");
            }

            logger.debug("Role check passed for role: {}", userRole);
        };
    }

    public io.javalin.http.Handler requireOwnership(Function<Context, UUID> getResourceUserId) {
        return ctx -> {
            UUID userId = ctx.attribute("userId");
            UUID resourceOwnerId = getResourceUserId.apply(ctx);
            String userRole = ctx.attribute("roles");

            boolean isAdmin = "ADMIN".equalsIgnoreCase(userRole);

            if (!isAdmin && !userId.equals(resourceOwnerId)) {
                logger.warn("User {} attempted to access resource owned by {}", userId, resourceOwnerId);
                throw new ForbiddenResponse("Access denied: you can only access your own resources");
            }

            logger.debug("Ownership check passed for user: {}", userId);
        };
    }

    private String extractToken(Context ctx) {
        String header = ctx.header("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            return null;
        }
        return header.substring(7);
    }
}