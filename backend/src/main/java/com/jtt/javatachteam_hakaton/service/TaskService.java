package com.jtt.javatachteam_hakaton.service;

import com.jtt.javatachteam_hakaton.api.dto.MistakesTaskDto;
import com.jtt.javatachteam_hakaton.api.dto.OpenTaskDto;
import com.jtt.javatachteam_hakaton.api.dto.TaskOptionDto;
import com.jtt.javatachteam_hakaton.api.dto.TestTaskDto;
import com.jtt.javatachteam_hakaton.entity.Attempt;
import com.jtt.javatachteam_hakaton.entity.Task;
import com.jtt.javatachteam_hakaton.entity.TaskOption;
import com.jtt.javatachteam_hakaton.entity.enums.TaskTypeEnum;
import com.jtt.javatachteam_hakaton.repository.AttemptRepository;
import com.jtt.javatachteam_hakaton.repository.TaskOptionRepository;
import com.jtt.javatachteam_hakaton.repository.TaskRepository;

import java.util.List;
import java.util.UUID;

public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskOptionRepository taskOptionRepository;
    private final AttemptRepository attemptRepository;
    private final SqlTaskEvaluationService sqlTaskEvaluationService;

    public TaskService(TaskRepository taskRepository,
                       TaskOptionRepository taskOptionRepository,
                       AttemptRepository attemptRepository,
                       SqlTaskEvaluationService sqlTaskEvaluationService) {
        this.taskRepository = taskRepository;
        this.taskOptionRepository = taskOptionRepository;
        this.attemptRepository = attemptRepository;
        this.sqlTaskEvaluationService = sqlTaskEvaluationService;
    }

    public List<Object> getTasksByType(String apiType, UUID userId) {
        if (apiType == null || apiType.isBlank()) {
            return taskRepository.findAll().stream()
                .sorted(java.util.Comparator.comparing(com.jtt.javatachteam_hakaton.entity.Task::getTitle))
                .map(task -> toDto(task, userId))
                .toList();
        }
        TaskTypeEnum taskType = fromApiType(apiType);
        return taskRepository.findByTaskType(taskType).stream()
            .map(task -> toDto(task, userId))
            .toList();
    }

    public Object getTaskById(UUID taskId, UUID userId) {
        Task task = taskRepository.findById(taskId)
            .orElseThrow(() -> new IllegalArgumentException("Задание не найдено"));
        return toDto(task, userId);
    }

    public static TaskTypeEnum fromApiType(String apiType) {
        return switch (apiType) {
            case "test" -> TaskTypeEnum.TEST;
            case "mistakes" -> TaskTypeEnum.ERROR_SEARCH;
            case "open" -> TaskTypeEnum.OPEN;
            default -> throw new IllegalArgumentException("Unsupported task type: " + apiType);
        };
    }

    private Object toDto(Task task, UUID userId) {
        List<TaskOption> options = taskOptionRepository.findByTaskId(task.getId());
        boolean solved = userId != null && attemptRepository.existsSolvedByUserIdAndTaskId(userId, task.getId());
        String question = buildQuestion(task);

        return switch (task.getTaskType()) {
            case TEST -> new TestTaskDto(
                task.getId().toString(),
                "test",
                defaultPoints(task),
                isMultiple(task, options),
                solved,
                question,
                options.stream()
                    .map(option -> new TaskOptionDto(option.getId().toString(), option.getContent()))
                    .toList()
            );
            case ERROR_SEARCH -> new MistakesTaskDto(
                task.getId().toString(),
                "mistakes",
                defaultPoints(task),
                solved,
                question,
                options.stream()
                    .map(TaskOption::getContent)
                    .reduce((left, right) -> left + "\n" + right)
                    .orElse("")
            );
            case OPEN -> new OpenTaskDto(
                task.getId().toString(),
                "open",
                defaultPoints(task),
                true,
                solved,
                question,
                latestWrittenText(userId, task.getId())
            );
        };
    }

    private String latestWrittenText(UUID userId, UUID taskId) {
        String starterSql = sqlTaskEvaluationService.getStarterSql(taskId);
        if (userId == null) {
            return starterSql;
        }

        return attemptRepository.findLatestByUserIdAndTaskId(userId, taskId)
            .map(Attempt::getWrittenText)
            .filter(text -> text != null && !text.isBlank())
            .orElse(starterSql);
    }

    private boolean isMultiple(Task task, List<TaskOption> options) {
        if (task.getCorrectAnswersCount() != null && task.getCorrectAnswersCount() > 0) {
            return task.getCorrectAnswersCount() > 1;
        }
        return options.stream().filter(TaskOption::getIsCorrect).count() > 1;
    }

    private int defaultPoints(Task task) {
        return task.getMaxPoints() == null ? 0 : task.getMaxPoints();
    }

    private String buildQuestion(Task task) {
        String title = task.getTitle() == null ? "" : task.getTitle().trim();
        String description = task.getDescription() == null ? "" : task.getDescription().trim();

        if (title.isEmpty()) {
            return description;
        }
        if (description.isEmpty() || title.equals(description)) {
            return title;
        }
        return title + "\n\n" + description;
    }
}
