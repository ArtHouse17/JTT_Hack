package com.jtt.javatachteam_hakaton.service;

import com.jtt.javatachteam_hakaton.api.dto.ProgressDto;
import com.jtt.javatachteam_hakaton.entity.Attempt;
import com.jtt.javatachteam_hakaton.entity.User;
import com.jtt.javatachteam_hakaton.entity.enums.StatusEnum;
import com.jtt.javatachteam_hakaton.entity.enums.TaskTypeEnum;
import com.jtt.javatachteam_hakaton.repository.AttemptRepository;
import com.jtt.javatachteam_hakaton.repository.TaskRepository;
import com.jtt.javatachteam_hakaton.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class UserService {
	private final UserRepository userRepository;
	private final AttemptRepository attemptRepository;
	private final TaskRepository taskRepository;

	public UserService(UserRepository userRepository, AttemptRepository attemptRepository, TaskRepository taskRepository) {
		this.userRepository = userRepository;
		this.attemptRepository = attemptRepository;
		this.taskRepository = taskRepository;
	}

	public User getCurrentUser(UUID userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new IllegalArgumentException("Пользователь не найден"));
	}

	public ProgressDto getUserProgress(UUID userId) {
		List<Attempt> attempts = attemptRepository.findByUserId(userId);

		int pointsEarned = 0;
		int testTasksSolved = 0;
		int mistakesTasksSolved = 0;
		int openTasksSolved = 0;

		Set<UUID> solvedTestTasks = new HashSet<>();
		Set<UUID> solvedMistakesTasks = new HashSet<>();
		Set<UUID> solvedOpenTasks = new HashSet<>();

		for (Attempt attempt : attempts) {
			if (attempt.getStatus() == StatusEnum.COMPLETED && attempt.getEarnedPoints() != null && attempt.getEarnedPoints() > 0) {
				if (attempt.getTask() != null && attempt.getTask().getTaskType() != null) {
					UUID taskId = attempt.getTask().getId();
					switch (attempt.getTask().getTaskType()) {
						case TEST -> {
							if (solvedTestTasks.add(taskId)) {
								pointsEarned += attempt.getEarnedPoints();
								testTasksSolved++;
							}
						}
						case ERROR_SEARCH -> {
							if (solvedMistakesTasks.add(taskId)) {
								pointsEarned += attempt.getEarnedPoints();
								mistakesTasksSolved++;
							}
						}
						case OPEN -> {
							if (solvedOpenTasks.add(taskId)) {
								pointsEarned += attempt.getEarnedPoints();
								openTasksSolved++;
							}
						}
					}
				}
			}
		}

		int testTotal = (int) taskRepository.countByTaskType(TaskTypeEnum.TEST);
		int mistakesTotal = (int) taskRepository.countByTaskType(TaskTypeEnum.ERROR_SEARCH);
		int openTotal = (int) taskRepository.countByTaskType(TaskTypeEnum.OPEN);
		int pointsTotal = taskRepository.sumMaxPointsByTaskType(TaskTypeEnum.TEST)
				+ taskRepository.sumMaxPointsByTaskType(TaskTypeEnum.ERROR_SEARCH)
				+ taskRepository.sumMaxPointsByTaskType(TaskTypeEnum.OPEN);

		return new ProgressDto(
				pointsTotal,
				pointsEarned,
				testTotal,
				testTasksSolved,
				mistakesTotal,
				mistakesTasksSolved,
				openTotal,
				openTasksSolved
		);
	}

	public ProgressDto resetUserProgress(UUID userId) {
		attemptRepository.deleteByUserId(userId);

		int testTotal = (int) taskRepository.countByTaskType(TaskTypeEnum.TEST);
		int mistakesTotal = (int) taskRepository.countByTaskType(TaskTypeEnum.ERROR_SEARCH);
		int openTotal = (int) taskRepository.countByTaskType(TaskTypeEnum.OPEN);
		int pointsTotal = taskRepository.sumMaxPointsByTaskType(TaskTypeEnum.TEST)
				+ taskRepository.sumMaxPointsByTaskType(TaskTypeEnum.ERROR_SEARCH)
				+ taskRepository.sumMaxPointsByTaskType(TaskTypeEnum.OPEN);

		return new ProgressDto(pointsTotal, 0, testTotal, 0, mistakesTotal, 0, openTotal, 0);
	}
}