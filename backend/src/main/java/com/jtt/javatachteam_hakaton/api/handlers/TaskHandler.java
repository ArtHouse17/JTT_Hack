package com.jtt.javatachteam_hakaton.api.handlers;

import com.fasterxml.jackson.databind.JsonNode;
import com.jtt.javatachteam_hakaton.entity.Attempt;
import com.jtt.javatachteam_hakaton.service.AttemptService;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class TaskHandler {
    private static final Logger logger = LoggerFactory.getLogger(TaskHandler.class);

    private final AttemptService attemptService;

    public TaskHandler(AttemptService attemptService) {
        this.attemptService = attemptService;
    }

    public void tasks(Context ctx) {
        // TODO: Реализовать получение всех заданий с пагинацией и фильтрацией
        ctx.status(501).result("Метод GET /tasks пока не реализован");
    }

    public void taskById(Context ctx) {
        // TODO: Реализовать получение задания по ID с деталями
        ctx.status(501).result("Метод GET /tasks/{taskId} пока не реализован");
    }

    public void submitTaskAttempt(Context ctx) {
        try {
            // userId уже установлен в middleware, не нужно проверять токен заново!
            UUID userId = ctx.attribute("userId");
            UUID taskId = UUID.fromString(ctx.pathParam("taskId"));

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

            logger.info("User {} submitted attempt for task {}", userId, taskId);

            Attempt attempt = attemptService.createAndCompleteAttempt(
                    userId, taskId, selectedOptionIds, textAnswer
            );

            boolean isSuccess = attempt.getEarnedPoints() > 0;
            ctx.json(new TaskAttemptResponse(isSuccess));

        } catch (IllegalArgumentException e) {
            logger.warn("Invalid task ID format: {}", ctx.pathParam("taskId"));
            ctx.status(400).json(Map.of("error", "Invalid task ID format"));
        } catch (Exception e) {
            logger.error("Error submitting attempt: {}", e.getMessage());
            ctx.status(500).json(Map.of("error", "Failed to submit attempt"));
        }
    }

    public record TaskAttemptResponse(boolean correct) {}
}