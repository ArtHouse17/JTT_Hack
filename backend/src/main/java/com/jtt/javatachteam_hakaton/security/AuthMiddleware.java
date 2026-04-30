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
            logger.warn("Отсутствует токен авторизации для запроса: {} {}",
                    ctx.method(), ctx.path());
            throw new UnauthorizedResponse("Отсутствует токен авторизации");
        }

        try {
            if (tokenBlacklistService.isBlacklisted(token)) {
                logger.warn("Для запроса использован токен из черного списка: {} {}",
                        ctx.method(), ctx.path());
                throw new UnauthorizedResponse("Токен недействителен. Пожалуйста, войдите снова.");
            }

            UUID userId = JwtProvider.extractUserId(token);

            if (JwtProvider.isExpired(token)) {
                logger.warn("Использованный пользователем токен просрочен: {}", userId);
                throw new UnauthorizedResponse("Срок действия токена истек");
            }

            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new UnauthorizedResponse("Пользователь не найден"));

            ctx.attribute("userId", userId);
            ctx.attribute("user", user);
            ctx.attribute("roles", user.getRole());
            ctx.attribute("token", token);

            logger.debug("Пользователь {} успешно аутентифицирован для выполнения запроса: {} {}",
                    userId, ctx.method(), ctx.path());

        } catch (ExpiredJwtException e) {
            logger.warn("Истекший JWT-токен: {}", e.getMessage());
            throw new UnauthorizedResponse("Срок действия токена истек. Пожалуйста, войдите снова.");
        } catch (MalformedJwtException e) {
            logger.warn("Некорректно сформированный JWT-токен: {}", e.getMessage());
            throw new UnauthorizedResponse("Неверный формат токена");
        } catch (SignatureException e) {
            logger.warn("Недействительная JWT-подпись: {}", e.getMessage());
            throw new UnauthorizedResponse("Недействительная подпись токена");
        } catch (Exception e) {
            logger.error("Непредвиденная ошибка во время аутентификации: {}", e.getMessage());
            throw new UnauthorizedResponse("Аутентификация не удалась");
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
            logger.debug("Дополнительная аутентификация не удалась: {}", e.getMessage());
        }
    }

    public io.javalin.http.Handler requireRole(String... allowedRoles) {
        Set<String> allowedRoleSet = Set.of(allowedRoles);

        return ctx -> {
            String userRole = ctx.attribute("roles");

            if (userRole == null) {
                logger.warn("Роль для пользователя не найдена, доступ запрещен");
                throw new ForbiddenResponse("Доступ запрещен: роль не назначена");
            }

            if (!allowedRoleSet.contains(userRole.toUpperCase())) {
                logger.warn("Пользователь с ролью {} попытался получить доступ к ресурсу, требующему определенных ролей: {}",
                        userRole, String.join(", ", allowedRoles));
                throw new ForbiddenResponse("Доступ запрещен: недостаточные права доступа");
            }

            logger.debug("Проверка соответствия роли пройдена: {}", userRole);
        };
    }

    public io.javalin.http.Handler requireOwnership(Function<Context, UUID> getResourceUserId) {
        return ctx -> {
            UUID userId = ctx.attribute("userId");
            UUID resourceOwnerId = getResourceUserId.apply(ctx);
            String userRole = ctx.attribute("roles");

            boolean isAdmin = "ADMIN".equalsIgnoreCase(userRole);

            if (!isAdmin && !userId.equals(resourceOwnerId)) {
                logger.warn("Пользователь {} попытался получить доступ к ресурсу, принадлежащему {}", userId, resourceOwnerId);
                throw new ForbiddenResponse("Доступ запрещен: вы можете получить доступ только к своим собственным ресурсам");
            }

            logger.debug("Проверка прав собственности для пользователя пройдена: {}", userId);
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