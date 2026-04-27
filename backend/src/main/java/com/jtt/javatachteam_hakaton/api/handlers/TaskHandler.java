package com.jtt.javatachteam_hakaton.api.handlers;

import com.fasterxml.jackson.databind.JsonNode;
import com.jtt.javatachteam_hakaton.config.JwtProvider;
import com.jtt.javatachteam_hakaton.entity.Attempt;
import com.jtt.javatachteam_hakaton.service.AttemptService;
import io.javalin.http.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskHandler {
    private final AttemptService attemptService;

    public TaskHandler(AttemptService attemptService) {
        this.attemptService = attemptService;
    }

    public void tasks(Context ctx) {
        ctx.status(501).result("Метод GET /tasks пока не реализован");
    }

    public void taskById(Context ctx) {
        ctx.status(501).result("Метод GET /tasks/{taskId} пока не реализован");
    }

    public void submitTaskAttempt(Context ctx) {
        UUID taskId = UUID.fromString(ctx.pathParam("taskId"));

        String authHeader = ctx.header("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            ctx.status(401).json("Необходима авторизация");
            return;
        }

        UUID userId;
        try {
            // Извлечение ID пользователя из JWT
            String token = authHeader.substring(7);
            userId = JwtProvider.extractUserId(token);
        } catch (Exception e) {
            ctx.status(401).json("Невалидный или просроченный токен");
            return;
        }

        JsonNode payload = ctx.bodyAsClass(JsonNode.class);
        JsonNode answerNode = payload.get("answer");

        List<UUID> selectedOptionIds = new ArrayList<>();
        String textAnswer = null;

        if (answerNode != null && answerNode.isArray()) {
            for (JsonNode node : answerNode) {
                selectedOptionIds.add(UUID.fromString(node.asText()));
            }
        } else if (answerNode != null && answerNode.isTextual()) {
            textAnswer = answerNode.asText();
        }

        Attempt attempt = attemptService.createAndCompleteAttempt(userId, taskId, selectedOptionIds, textAnswer);
        boolean isSuccess = attempt.getEarnedPoints() > 0;

        ctx.json(new TaskAttemptResponse(isSuccess));
    }

    public record TaskAttemptResponse(boolean correct) {}
}