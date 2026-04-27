package com.jtt.javatachteam_hakaton.service;

import com.jtt.javatachteam_hakaton.api.dto.ProgressDto;
import com.jtt.javatachteam_hakaton.repository.AttemptRepository;
import com.jtt.javatachteam_hakaton.repository.UserRepository;

import java.util.UUID;

public class UserService {
    private final UserRepository userRepository;
    private final AttemptRepository attemptRepository;

    public UserService(UserRepository userRepository, AttemptRepository attemptRepository) {
        this.userRepository = userRepository;
        this.attemptRepository = attemptRepository;
    }

	// public User getCurrentUser(UUID userId) {
	// 	return userRepository.findById(userId)
	// 			.orElseThrow(() -> new IllegalArgumentException("Пользователь не найден"));
	// }

    public ProgressDto getUserProgress(UUID userId) {
        // TODO: Реализовать сбор статистики (общее количество баллов, пройденные задания).
        // Для этого может понадобиться добавить метод findByUserId в AttemptRepository.
        
        return new ProgressDto(
            30,
            12,
            5,
            3,
            4,
            2,
            6,
            1
        );
    }

    public ProgressDto resetUserProgress(UUID userId) {
        // TODO: Реализовать удаление всех попыток пользователя из БД.
        // attemptRepository.deleteByUserId(userId);
        
        return new ProgressDto(
            30,
            0,
            5,
            0,
            4,
            0,
            6,
            0
        );
    }
}
