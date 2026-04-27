package com.jtt.javatachteam_hakaton.api.handlers;

import com.jtt.javatachteam_hakaton.api.dto.ProgressDto;
import com.jtt.javatachteam_hakaton.api.dto.UserInfoDto;
import com.jtt.javatachteam_hakaton.config.JwtProvider;
import com.jtt.javatachteam_hakaton.entity.User;
import com.jtt.javatachteam_hakaton.repository.AttemptRepository;
import com.jtt.javatachteam_hakaton.repository.UserRepository;
import com.jtt.javatachteam_hakaton.service.UserService;
import io.javalin.http.Context;
import io.javalin.http.UnauthorizedResponse;

import java.util.UUID;

public class UserHandler {
    private final UserService userService;
    private final UserRepository userRepository;
    private final AttemptRepository attemptRepository;

    public UserHandler(UserService userService, UserRepository userRepository, AttemptRepository attemptRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.attemptRepository = attemptRepository;
    }

    private UUID getUserIdFromToken(Context ctx) {
        // String authHeader = ctx.header("Authorization");
        // if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        //     throw new UnauthorizedResponse("Необходима авторизация");
        // }
        // try {
        //     return JwtProvider.extractUserId(authHeader.substring(7));
        // } catch (Exception e) {
        //     throw new UnauthorizedResponse("Невалидный токен");
        // }
        
        return UUID.fromString("11111111-1111-1111-1111-111111111111");
    }

    public void currentUser(Context ctx) {
        UUID userId = getUserIdFromToken(ctx);
        ctx.json(new UserInfoDto("la_sima"));
    }

    public void currentUserProgress(Context ctx) {
        UUID userId = getUserIdFromToken(ctx);
        ctx.json(userService.getUserProgress(userId));
    }

    public void resetCurrentUserProgress(Context ctx) {
        UUID userId = getUserIdFromToken(ctx);
        ctx.json(userService.resetUserProgress(userId));
    }
}
