package com.jtt.javatachteam_hakaton.service;

import com.jtt.javatachteam_hakaton.api.handlers.UserHandler;
import com.jtt.javatachteam_hakaton.entity.User;
import com.jtt.javatachteam_hakaton.repository.AttemptRepository;
import com.jtt.javatachteam_hakaton.repository.UserRepository;
import com.jtt.javatachteam_hakaton.entity.enums.GradeEnum;

import java.util.UUID;

public class UserService {
	private final UserRepository userRepository;
	private final AttemptRepository attemptRepository;

	public UserService(UserRepository userRepository, AttemptRepository attemptRepository) {
		this.userRepository = userRepository;
		this.attemptRepository = attemptRepository;
	}

	public User getCurrentUser(UUID userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new IllegalArgumentException("Пользователь не найден"));
	}

	public Object getUserProgress(UUID userId) {
		// TODO: Реализовать сбор статистики (общее количество баллов, пройденные задания).
		// Для этого может понадобиться добавить метод findByUserId в AttemptRepository.
		return "{\"message\": \"Здесь будет прогресс пользователя\"}";
	}

	public Object resetUserProgress(UUID userId) {
		// TODO: Реализовать удаление всех попыток пользователя из БД.
		// attemptRepository.deleteByUserId(userId);
		return "{\"message\": \"Прогресс успешно сброшен\"}";
	}

	// UserService.java - добавить эти методы
	public void updateUser(UUID userId, UserHandler.UpdateUserRequest req) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new IllegalArgumentException("User not found"));

		if (req.firstname() != null) user.setFirstname(req.firstname());
		if (req.lastname() != null) user.setLastname(req.lastname());
		if (req.gradeLevel() != null) {
			user.setGradeLevel(GradeEnum.valueOf(req.gradeLevel()));
		}

		userRepository.save(user);
	}
}