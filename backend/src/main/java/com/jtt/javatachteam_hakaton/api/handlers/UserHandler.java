package com.jtt.javatachteam_hakaton.api.handlers;

import com.jtt.javatachteam_hakaton.entity.User;
import com.jtt.javatachteam_hakaton.security.Role;
import com.jtt.javatachteam_hakaton.service.UserService;
import io.javalin.http.Context;
import io.javalin.http.ForbiddenResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class UserHandler {
    private static final Logger logger = LoggerFactory.getLogger(UserHandler.class);

    private final UserService userService;

    public UserHandler(UserService userService) {
        this.userService = userService;
    }

    /**
     * Получение текущего пользователя
     * userId уже установлен в middleware
     */
    public void currentUser(Context ctx) {
        UUID userId = ctx.attribute("userId");
        logger.debug("Getting current user: {}", userId);

        User user = userService.getCurrentUser(userId);

        ctx.json(new UserProfileResponse(
                user.getId().toString(),
                user.getUsername(),
                user.getFirstname(),
                user.getLastname(),
                user.getGradeLevel() != null ? user.getGradeLevel().name() : null
        ));
    }

    /**
     * Получение прогресса пользователя
     */
    public void currentUserProgress(Context ctx) {
        UUID userId = ctx.attribute("userId");
        logger.debug("Getting progress for user: {}", userId);

        ctx.json(userService.getUserProgress(userId));
    }

    /**
     * Сброс прогресса пользователя
     */
    public void resetCurrentUserProgress(Context ctx) {
        UUID userId = ctx.attribute("userId");
        logger.info("Resetting progress for user: {}", userId);

        ctx.json(userService.resetUserProgress(userId));
    }

    /**
     * Получение пользователя по ID (только для ADMIN)
     */
    public void getUserById(Context ctx) {
        // Проверяем роль ADMIN (дополнительная проверка, хотя уже есть в middleware)
        String userRole = ctx.attribute("roles");
        if (!Role.ADMIN.getValue().equals(userRole)) {
            throw new ForbiddenResponse("Only admins can view other users");
        }

        UUID userId = UUID.fromString(ctx.pathParam("id"));
        User user = userService.getCurrentUser(userId);

        ctx.json(new UserProfileResponse(
                user.getId().toString(),
                user.getUsername(),
                user.getFirstname(),
                user.getLastname(),
                user.getGradeLevel() != null ? user.getGradeLevel().name() : null
        ));
    }

    /**
     * Обновление пользователя с проверкой прав
     */
    public void updateUser(Context ctx) {
        UUID userIdFromToken = ctx.attribute("userId");
        UUID userIdToUpdate = UUID.fromString(ctx.pathParam("id"));
        String userRole = ctx.attribute("roles");

        // Проверяем: либо ADMIN, либо обновляем своего пользователя
        boolean isAdmin = Role.ADMIN.getValue().equals(userRole);
        boolean isSelf = userIdFromToken.equals(userIdToUpdate);

        if (!isAdmin && !isSelf) {
            logger.warn("User {} attempted to update user {}", userIdFromToken, userIdToUpdate);
            throw new ForbiddenResponse("Cannot modify other users");
        }

        UpdateUserRequest req = ctx.bodyAsClass(UpdateUserRequest.class);
        // TODO: реализовать обновление пользователя
        userService.updateUser(userIdToUpdate, req);

        ctx.status(204);
    }

    // DTO для безопасного ответа
    public record UserProfileResponse(String id, String username, String firstname,
                                      String lastname, String gradeLevel) {}

    public record UpdateUserRequest(String firstname, String lastname, String gradeLevel) {}
}