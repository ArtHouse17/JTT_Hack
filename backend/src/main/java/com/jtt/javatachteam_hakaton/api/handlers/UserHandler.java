package com.jtt.javatachteam_hakaton.api.handlers;

import com.jtt.javatachteam_hakaton.api.AuthUtils;
import com.jtt.javatachteam_hakaton.entity.User;
import com.jtt.javatachteam_hakaton.service.UserService;
import io.javalin.http.Context;

import java.util.UUID;

public class UserHandler {
    private final UserService userService;

    public UserHandler(UserService userService) {
        this.userService = userService;
    }

    public void currentUser(Context ctx) {
        UUID userId = AuthUtils.requireUserId(ctx);
        User user = userService.getCurrentUser(userId);

        ctx.json(new UserProfileResponse(
                user.getId().toString(),
                user.getUsername(),
                user.getFirstname(),
                user.getLastname(),
                user.getGradeLevel() != null ? user.getGradeLevel().name() : null
        ));
    }

    public void currentUserProgress(Context ctx) {
        UUID userId = AuthUtils.requireUserId(ctx);
        ctx.json(userService.getUserProgress(userId));
    }

    public void resetCurrentUserProgress(Context ctx) {
        UUID userId = AuthUtils.requireUserId(ctx);

        userService.resetUserProgress(userId);

        ctx.json(new MessageResponse("Прогресс успешно сброшен"));
    }

    public record UserProfileResponse(String id, String username, String firstname, String lastname, String gradeLevel) {}

    public record MessageResponse(String message) {}
}