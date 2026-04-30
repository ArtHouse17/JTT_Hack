package com.jtt.javatachteam_hakaton.service;

import com.jtt.javatachteam_hakaton.entity.*;
import com.jtt.javatachteam_hakaton.entity.enums.StatusEnum;
import com.jtt.javatachteam_hakaton.entity.enums.TaskTypeEnum;
import com.jtt.javatachteam_hakaton.repository.AttemptAnswerRepository;
import com.jtt.javatachteam_hakaton.repository.AttemptRepository;
import com.jtt.javatachteam_hakaton.repository.TaskOptionRepository;
import com.jtt.javatachteam_hakaton.repository.TaskRepository;
import com.jtt.javatachteam_hakaton.repository.UserRepository;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class AttemptService {
	private final AttemptRepository attemptRepository;
	private final AttemptAnswerRepository attemptAnswerRepository;
	private final TaskRepository taskRepository;
	private final UserRepository userRepository;
	private final TaskOptionRepository taskOptionRepository;

	public AttemptService(AttemptRepository attemptRepository,
	                      AttemptAnswerRepository attemptAnswerRepository,
	                      TaskRepository taskRepository,
	                      UserRepository userRepository,
	                      TaskOptionRepository taskOptionRepository) {
		this.attemptRepository = attemptRepository;
		this.attemptAnswerRepository = attemptAnswerRepository;
		this.taskRepository = taskRepository;
		this.userRepository = userRepository;
		this.taskOptionRepository = taskOptionRepository;
	}

	public Attempt createAndCompleteAttempt(UUID userId, UUID taskId, List<UUID> selectedOptionIds, String textAnswer) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new IllegalArgumentException("Пользователь не найден"));
		Task task = taskRepository.findById(taskId)
				.orElseThrow(() -> new IllegalArgumentException("Задание не найдено"));
		List<TaskOption> taskOptions = taskOptionRepository.findByTaskId(task.getId());
		validateAnswerFormat(task.getTaskType(), selectedOptionIds, textAnswer);

		Attempt attempt = new Attempt();
		attempt.setUser(user);
		attempt.setTask(task);
		attempt.setStartedAt(Instant.now());
		attempt.setStatus(StatusEnum.COMPLETED);
		attempt.setCompletedAt(Instant.now());

		int earnedPoints = 0;

		if (task.getTaskType() == TaskTypeEnum.TEST) {
			earnedPoints = calculateTestPoints(task, taskOptions, selectedOptionIds);

		} else if (task.getTaskType() == TaskTypeEnum.ERROR_SEARCH) {
			attempt.setWrittenText(textAnswer);
			earnedPoints = calculateTextMatchPoints(task, taskOptions, textAnswer);

		} else if (task.getTaskType() == TaskTypeEnum.OPEN) {
			attempt.setWrittenText(textAnswer);
			earnedPoints = calculateTextMatchPoints(task, taskOptions, textAnswer);
		}

		attempt.setEarnedPoints(earnedPoints);
		Attempt savedAttempt = attemptRepository.save(attempt);

		if (task.getTaskType() == TaskTypeEnum.TEST && selectedOptionIds != null) {
			saveAttemptAnswers(savedAttempt, taskOptions, selectedOptionIds);
		}

		return savedAttempt;
	}

	private void validateAnswerFormat(TaskTypeEnum taskType, List<UUID> selectedOptionIds, String textAnswer) {
		boolean hasSelectedOptions = selectedOptionIds != null && !selectedOptionIds.isEmpty();
		boolean hasTextAnswer = textAnswer != null;

		if (taskType == TaskTypeEnum.TEST && hasTextAnswer) {
			throw new IllegalArgumentException("Для тестового задания answer должен быть массивом option id");
		}

		if ((taskType == TaskTypeEnum.ERROR_SEARCH || taskType == TaskTypeEnum.OPEN) && hasSelectedOptions) {
			throw new IllegalArgumentException("Для текстового задания answer должен быть строкой");
		}
	}

	private int calculateTestPoints(Task task, List<TaskOption> taskOptions, List<UUID> selectedOptionIds) {
		if (selectedOptionIds == null || selectedOptionIds.isEmpty()) return 0;

		Set<UUID> selectedIds = new HashSet<>(selectedOptionIds);
		long correctCount = taskOptions.stream().filter(TaskOption::getIsCorrect).count();
		long userCorrect = taskOptions.stream()
				.filter(opt -> selectedOptionIds.contains(opt.getId()) && opt.getIsCorrect()).count();
		long userWrong = taskOptions.stream()
				.filter(opt -> selectedOptionIds.contains(opt.getId()) && !opt.getIsCorrect()).count();
		long validSelected = taskOptions.stream()
				.filter(opt -> selectedIds.contains(opt.getId()))
				.count();

		if (userWrong > 0 || correctCount == 0 || validSelected != selectedIds.size()) return 0;
		return (int) Math.round(((double) userCorrect / correctCount) * task.getMaxPoints());
	}

	private int calculateTextMatchPoints(Task task, List<TaskOption> taskOptions, String textAnswer) {
		if (textAnswer == null || textAnswer.trim().isEmpty()) return 0;

		String correctAnswer = taskOptions.stream()
				.filter(TaskOption::getIsCorrect)
				.map(TaskOption::getContent)
				.findFirst()
				.orElse("");

		String normalizedUserAnswer = textAnswer.replaceAll("\\s+", " ").trim().toLowerCase();
		String normalizedCorrectAnswer = correctAnswer.replaceAll("\\s+", " ").trim().toLowerCase();

		if (normalizedUserAnswer.equals(normalizedCorrectAnswer)) {
			return task.getMaxPoints();
		}
		return 0;
	}

	private void saveAttemptAnswers(Attempt attempt, List<TaskOption> taskOptions, List<UUID> optionIds) {
		Set<UUID> optionIdSet = new HashSet<>(optionIds);
		for (TaskOption option : taskOptions) {
			if (optionIdSet.contains(option.getId())) {
				AttemptAnswer answer = new AttemptAnswer();
				answer.setAttempt(attempt);
				answer.setTaskOption(option);
				attemptAnswerRepository.save(answer);
			}
		}
	}
}
