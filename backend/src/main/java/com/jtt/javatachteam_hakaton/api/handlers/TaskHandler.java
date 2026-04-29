package com.jtt.javatachteam_hakaton.api.handlers;

import com.jtt.javatachteam_hakaton.api.dto.TaskAttemptRequestDto;
import com.jtt.javatachteam_hakaton.api.dto.TaskAttemptResponseDto;
import com.jtt.javatachteam_hakaton.config.JwtProvider;
import com.jtt.javatachteam_hakaton.entity.Attempt;
import com.jtt.javatachteam_hakaton.service.AttemptService;
import com.jtt.javatachteam_hakaton.service.TaskService;
import io.javalin.http.Context;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.UnauthorizedResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TaskHandler {
    private final TaskService taskService;
    private final AttemptService attemptService;

    public TaskHandler(TaskService taskService, AttemptService attemptService) {
        this.taskService = taskService;
        this.attemptService = attemptService;
    }

    public void tasks(Context ctx) {
        String type = ctx.queryParam("type");
        UUID userId = getUserIdFromToken(ctx, false).orElse(null);

        try {
            ctx.json(taskService.getTasksByType(type, userId));
        } catch (IllegalArgumentException exception) {
            throw new BadRequestResponse(exception.getMessage());
        }
    }

    public void taskById(Context ctx) {
        UUID taskId = parseTaskId(ctx);
        UUID userId = getUserIdFromToken(ctx, false).orElse(null);

        try {
            ctx.json(taskService.getTaskById(taskId, userId));
        } catch (IllegalArgumentException exception) {
            throw new NotFoundResponse(exception.getMessage());
        }
    }

    public void submitTaskAttempt(Context ctx) {
        UUID taskId = parseTaskId(ctx);
        UUID userId = getUserIdFromToken(ctx, true)
            .orElseThrow(() -> new UnauthorizedResponse("Необходима авторизация"));

        List<UUID> selectedOptionIds = new ArrayList<>();
        String textAnswer = null;
        TaskAttemptRequestDto payload = ctx.bodyAsClass(TaskAttemptRequestDto.class);

        if (payload.answer() instanceof List<?> answers) {
            for (Object answer : answers) {
                if (!(answer instanceof String optionId)) {
                    throw new BadRequestResponse("For test tasks answer must contain option ids");
                }
                try {
                    selectedOptionIds.add(UUID.fromString(optionId));
                } catch (IllegalArgumentException exception) {
                    throw new BadRequestResponse("Invalid option id: " + optionId);
                }
            }
        } else if (payload.answer() instanceof String answer) {
            textAnswer = answer;
        } else {
            throw new BadRequestResponse("Field 'answer' must be either string or array of strings");
        }

        Attempt attempt;
        try {
            attempt = attemptService.createAndCompleteAttempt(userId, taskId, selectedOptionIds, textAnswer);
        } catch (IllegalArgumentException exception) {
            if (exception.getMessage() != null && exception.getMessage().endsWith("не найдено")) {
                throw new NotFoundResponse(exception.getMessage());
            }
            throw new BadRequestResponse(exception.getMessage());
        }
        boolean isSuccess = attempt.getEarnedPoints() > 0;

        ctx.json(new TaskAttemptResponseDto(isSuccess));
    }

    private UUID parseTaskId(Context ctx) {
        try {
            return UUID.fromString(ctx.pathParam("taskId"));
        } catch (IllegalArgumentException exception) {
            throw new BadRequestResponse("Invalid taskId");
        }
    }

    private Optional<UUID> getUserIdFromToken(Context ctx, boolean required) {
        String authHeader = ctx.header("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            if (required) {
                throw new UnauthorizedResponse("Необходима авторизация");
            }
            return Optional.empty();
        }

        try {
            return Optional.of(JwtProvider.extractUserId(authHeader.substring(7)));
        } catch (Exception exception) {
            if (required) {
                throw new UnauthorizedResponse("Невалидный или просроченный токен");
            }
            return Optional.empty();
        }
    }
}
