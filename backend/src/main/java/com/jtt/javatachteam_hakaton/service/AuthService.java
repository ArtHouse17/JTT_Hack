package com.jtt.javatachteam_hakaton.service;

import com.jtt.javatachteam_hakaton.config.JwtProvider;
import com.jtt.javatachteam_hakaton.entity.User;
import com.jtt.javatachteam_hakaton.entity.enums.GradeEnum;
import com.jtt.javatachteam_hakaton.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;

import java.time.Instant;
import java.util.Optional;

public class AuthService {
	private final UserRepository userRepository;

	public AuthService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public String login(String username, String rawPassword) {
		String trimmedUsername = username == null ? "" : username.trim();
		String trimmedPassword = rawPassword == null ? "" : rawPassword.trim();

		if (trimmedUsername.isEmpty() || trimmedPassword.isEmpty()) {
			throw new IllegalArgumentException("Логин и пароль обязательны");
		}

		Optional<User> userOpt = userRepository.findByUsername(trimmedUsername);

		if (userOpt.isPresent()) {
			User user = userOpt.get();
			if (BCrypt.checkpw(trimmedPassword, user.getPassHash())) {
				return JwtProvider.generateToken(user.getId());
			}
		}
		throw new IllegalArgumentException("Неверный логин или пароль");
	}

	public String signup(String username, String rawPassword, String firstname, String lastname) {
		String trimmedUsername = username == null ? "" : username.trim();
		String trimmedPassword = rawPassword == null ? "" : rawPassword.trim();

		if (trimmedUsername.isEmpty() || trimmedPassword.isEmpty()) {
			throw new IllegalArgumentException("Логин и пароль обязательны");
		}

		if (trimmedPassword.length() < 6) {
			throw new IllegalArgumentException("Пароль должен содержать минимум 6 символов");
		}

		if (userRepository.findByUsername(trimmedUsername).isPresent()) {
			throw new IllegalArgumentException("Пользователь с таким логином уже существует");
		}

		User user = new User();
		user.setUsername(trimmedUsername);
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setRole("USER");
		user.setGradeLevel(GradeEnum.Junior);
		user.setCreatedAt(Instant.now());

		String hashedPass = BCrypt.hashpw(trimmedPassword, BCrypt.gensalt());
		user.setPassHash(hashedPass);

		User savedUser = userRepository.save(user);

		// Сразу авторизуем после регистрации
		return JwtProvider.generateToken(savedUser.getId());
	}
}