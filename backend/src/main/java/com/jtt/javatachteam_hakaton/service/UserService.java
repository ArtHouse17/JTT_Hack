package com.jtt.javatachteam_hakaton.service;

import com.jtt.javatachteam_hakaton.api.dto.ProgressDto;
import com.jtt.javatachteam_hakaton.entity.Attempt;
import com.jtt.javatachteam_hakaton.entity.User;
import com.jtt.javatachteam_hakaton.entity.enums.StatusEnum;
import com.jtt.javatachteam_hakaton.repository.AttemptRepository;
import com.jtt.javatachteam_hakaton.repository.UserRepository;

import java.util.List;
import java.util.UUID;

public class UserService {
	private final UserRepository userRepository;
	private final AttemptRepository attemptRepository;
	// TODO: В будущем сюда можно добавить TaskRepository,
	// чтобы доставать общее количество всех заданий в системе для полей "Total".

	public UserService(UserRepository userRepository, AttemptRepository attemptRepository) {
		this.userRepository = userRepository;
		this.attemptRepository = attemptRepository;
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

		for (Attempt attempt : attempts) {
			if (attempt.getStatus() == StatusEnum.COMPLETED) {
				pointsEarned += attempt.getEarnedPoints() != null ? attempt.getEarnedPoints() : 0;

				if (attempt.getTask() != null && attempt.getTask().getTaskType() != null) {
					String taskType = attempt.getTask().getTaskType().name();

					switch (taskType) {
						case "TEST" -> testTasksSolved++;
						case "MISTAKES" -> mistakesTasksSolved++;
						case "OPEN" -> openTasksSolved++;
					}
				}
			}
		}

		return new ProgressDto(
				0,
				pointsEarned,
				0,
				testTasksSolved,
				0,
				mistakesTasksSolved,
				0,
				openTasksSolved
		);
	}

	public void resetUserProgress(UUID userId) {
		attemptRepository.deleteByUserId(userId);
	}
}