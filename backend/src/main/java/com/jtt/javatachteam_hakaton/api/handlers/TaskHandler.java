package com.jtt.javatachteam_hakaton.api.handlers;

import com.fasterxml.jackson.databind.JsonNode;
import com.jtt.javatachteam_hakaton.api.dto.*;
import com.jtt.javatachteam_hakaton.config.JwtProvider;
import com.jtt.javatachteam_hakaton.entity.Attempt;
import com.jtt.javatachteam_hakaton.entity.Task;
import com.jtt.javatachteam_hakaton.entity.TaskOption;
import com.jtt.javatachteam_hakaton.entity.enums.TaskTypeEnum;
import com.jtt.javatachteam_hakaton.repository.AttemptRepository;
import com.jtt.javatachteam_hakaton.repository.TaskRepository;
import com.jtt.javatachteam_hakaton.service.AttemptService;
import io.javalin.http.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskHandler {
    private final AttemptService attemptService;
    private final TaskRepository taskRepository;
    private final AttemptRepository attemptRepository;

    public TaskHandler(AttemptService attemptService, TaskRepository taskRepository, AttemptRepository attemptRepository) {
        this.attemptService = attemptService;
        this.taskRepository = taskRepository;
        this.attemptRepository = attemptRepository;
    }

    public void tasks(Context ctx) {
        String type = ctx.queryParam("type");
        
        if (type == null) {
            List<Object> allTasks = new ArrayList<>();
            allTasks.addAll(getMockTestTasks());
            allTasks.addAll(getMockMistakesTasks());
            allTasks.addAll(getMockOpenTasks());
            ctx.json(allTasks);
        } else if (type.equals("test")) {
            ctx.json(getMockTestTasks());
        } else if (type.equals("mistakes")) {
            ctx.json(getMockMistakesTasks());
        } else if (type.equals("open")) {
            ctx.json(getMockOpenTasks());
        } else {
            ctx.json(List.of());
        }
    }

    public void taskById(Context ctx) {
        UUID taskId = UUID.fromString(ctx.pathParam("taskId"));
        String type = ctx.queryParam("type");
        
        if (type == null || type.equals("test")) {
            ctx.json(getMockTestTasks().get(0));
        } else if (type.equals("mistakes")) {
            ctx.json(getMockMistakesTasks().get(0));
        } else if (type.equals("open")) {
            ctx.json(getMockOpenTasks().get(0));
        } else {
            ctx.status(404);
        }
    }

    public void submitTaskAttempt(Context ctx) {
        UUID taskId = UUID.fromString(ctx.pathParam("taskId"));
        
        // String authHeader = ctx.header("Authorization");
        // if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        //     ctx.status(401).json("Необходима авторизация");
        //     return;
        // }
        // UUID userId;
        // try {
        //     String token = authHeader.substring(7);
        //     userId = JwtProvider.extractUserId(token);
        // } catch (Exception e) {
        //     ctx.status(401).json("Невалидный или просроченный токен");
        //     return;
        // }
        // JsonNode payload = ctx.bodyAsClass(JsonNode.class);
        // JsonNode answerNode = payload.get("answer");
        // List<UUID> selectedOptionIds = new ArrayList<>();
        // String textAnswer = null;
        // if (answerNode != null && answerNode.isArray()) {
        //     for (JsonNode node : answerNode) {
        //         selectedOptionIds.add(UUID.fromString(node.asText()));
        //     }
        // } else if (answerNode != null && answerNode.isTextual()) {
        //     textAnswer = answerNode.asText();
        // }
        // Attempt attempt = attemptService.createAndCompleteAttempt(userId, taskId, selectedOptionIds, textAnswer);
        // boolean isSuccess = attempt.getEarnedPoints() > 0;
        // ctx.json(new TaskAttemptResponse(isSuccess));
        
        ctx.json(new TaskAttemptResponseDto(true));
    }

    private List<TestTaskDto> getMockTestTasks() {
        return List.of(
            new TestTaskDto(
                "c044082b-b0da-4998-b3bd-9ba2217668c4",
                "test",
                1,
                false,
                false,
                "Как расшифровывается аббревиатура SQL?",
                List.of(
                    new TaskOptionDto("ebcd3eb4-9b40-476d-865f-48c6ceb97f14", "Structured Query Language"),
                    new TaskOptionDto("050d5eaf-d00d-402e-9cfd-aa85a7e52c64", "Structured Questionable Logic"),
                    new TaskOptionDto("b15cc70f-828e-46f4-94eb-8df450dec83c", "Slightly Quirky Language"),
                    new TaskOptionDto("3f2c4ab3-da69-4b0c-8df9-a0863e5999e1", "Simple Query Language")
                )
            ),
            new TestTaskDto(
                "d155193c-c1eb-4aa8-9cce-cc3322786fd5",
                "test",
                1,
                true,
                false,
                "Как расшифровывается аббревиатура SQL?",
                List.of(
                    new TaskOptionDto("ebcd3eb4-9b40-476d-865f-48c6ceb97f14", "Structured Query Language"),
                    new TaskOptionDto("050d5eaf-d00d-402e-9cfd-aa85a7e52c64", "Structured Questionable Logic"),
                    new TaskOptionDto("b15cc70f-828e-46f4-94eb-8df450dec83c", "Slightly Quirky Language"),
                    new TaskOptionDto("3f2c4ab3-da69-4b0c-8df9-a0863e5999e1", "Simple Query Language")
                )
            )
        );
    }

    private List<MistakesTaskDto> getMockMistakesTasks() {
        return List.of(
            new MistakesTaskDto(
                "ebcd3eb4-9b40-476d-865f-48c6ceb97f14",
                "mistakes",
                2,
                false,
                "Исправьте ошибку",
                "Ошыбка"
            )
        );
    }

    private List<OpenTaskDto> getMockOpenTasks() {
        return List.of(
            new OpenTaskDto(
                "f3dc4275-e8f1-4a3b-9c2d-1e5f6a7b8c9d",
                "open",
                3,
                true,
                false,
                "Напишите SQL-запрос для выборки имён сотрудников с зарплатой выше средней.",
                "select * from Employee;"
            )
        );
    }
}
