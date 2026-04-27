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
		Optional<User> userOpt = userRepository.findByUsername(username);

		if (userOpt.isPresent()) {
			User user = userOpt.get();
			// Проверяем, совпадает ли введенный пароль с хешем из БД
			if (BCrypt.checkpw(rawPassword, user.getPassHash())) {
				// Если совпадает — генерируем токен
				return JwtProvider.generateToken(user.getId());
			}
		}
		// Возвращаем null, если логин или пароль неверны
		return null;
	}

	public String signup(String username, String rawPassword, String firstname, String lastname) {
		// Проверяем, не занят ли логин
		if (userRepository.findByUsername(username).isPresent()) {
			throw new IllegalArgumentException("Пользователь с таким именем уже существует");
		}

		User user = new User();
		user.setUsername(username);
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setRole("USER");
		user.setGradeLevel(GradeEnum.Junior); // Грейд по умолчанию
		user.setCreatedAt(Instant.now());

		// Хешируем пароль перед сохранением в БД
		String hashedPass = BCrypt.hashpw(rawPassword, BCrypt.gensalt());
		user.setPassHash(hashedPass);

		// Внимание: Убедись, что метод save в UserRepository открывает и закрывает транзакцию!
		User savedUser = userRepository.save(user);

		// Сразу авторизуем после регистрации
		return JwtProvider.generateToken(savedUser.getId());
	}
}