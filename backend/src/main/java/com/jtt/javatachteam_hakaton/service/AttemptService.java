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
import java.util.List;
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

		Attempt attempt = new Attempt();
		attempt.setUser(user);
		attempt.setTask(task);
		attempt.setStartedAt(Instant.now());
		attempt.setStatus(StatusEnum.COMPLETED);
		attempt.setCompletedAt(Instant.now());

		int earnedPoints = 0;

		if (task.getTaskType() == TaskTypeEnum.TEST) {
			earnedPoints = calculateTestPoints(task, selectedOptionIds);

		} else if (task.getTaskType() == TaskTypeEnum.ERROR_SEARCH) {
			attempt.setWrittenText(textAnswer);
			earnedPoints = calculateErrorSearchPoints(task, textAnswer);

		} else if (task.getTaskType() == TaskTypeEnum.OPEN) {
			attempt.setWrittenText(textAnswer);
			// TODO: Реализовать SQL-тренажер.
			// Пользователь пишет SQL-запрос на фронтенде -> мы прогоняем его на бэкенде в изолированной БД (песочнице)
			// -> сравниваем вывод с эталонным результатом из нашей БД -> начисляем баллы.
			earnedPoints = 0;
		}

		attempt.setEarnedPoints(earnedPoints);
		Attempt savedAttempt = attemptRepository.save(attempt);

		if (task.getTaskType() == TaskTypeEnum.TEST && selectedOptionIds != null) {
			saveAttemptAnswers(savedAttempt, selectedOptionIds);
		}

		return savedAttempt;
	}

	private int calculateTestPoints(Task task, List<UUID> selectedOptionIds) {
		if (selectedOptionIds == null || selectedOptionIds.isEmpty()) return 0;

		List<TaskOption> options = taskOptionRepository.findByTaskId(task.getId());
		long correctCount = options.stream().filter(TaskOption::getIsCorrect).count();
		long userCorrect = options.stream()
				.filter(opt -> selectedOptionIds.contains(opt.getId()) && opt.getIsCorrect()).count();
		long userWrong = options.stream()
				.filter(opt -> selectedOptionIds.contains(opt.getId()) && !opt.getIsCorrect()).count();

		if (userWrong > 0 || correctCount == 0) return 0;
		return (int) Math.round(((double) userCorrect / correctCount) * task.getMaxPoints());
	}

	// Автопроверка заданий на поиск ошибок
	private int calculateErrorSearchPoints(Task task, String textAnswer) {
		if (textAnswer == null || textAnswer.trim().isEmpty()) return 0;

		List<TaskOption> options = taskOptionRepository.findByTaskId(task.getId());
		String correctAnswer = options.stream()
				.filter(TaskOption::getIsCorrect)
				.map(TaskOption::getContent)
				.findFirst()
				.orElse("");

		// Нормализация
		String normalizedUserAnswer = textAnswer.replaceAll("\\s+", " ").trim().toLowerCase();
		String normalizedCorrectAnswer = correctAnswer.replaceAll("\\s+", " ").trim().toLowerCase();

		if (normalizedUserAnswer.equals(normalizedCorrectAnswer)) {
			return task.getMaxPoints();
		}
		return 0;
	}

	// Сохранение выбранных вариантов
	private void saveAttemptAnswers(Attempt attempt, List<UUID> optionIds) {
		for (UUID optionId : optionIds) {
			taskOptionRepository.findById(optionId).ifPresent(option -> {
				AttemptAnswer answer = new AttemptAnswer();
				answer.setAttempt(attempt);
				answer.setTaskOption(option);
				attemptAnswerRepository.save(answer);
			});
		}
	}
}