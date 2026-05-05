package com.jtt.javatachteam_hakaton.service;

import com.jtt.javatachteam_hakaton.config.JwtProvider;
import com.jtt.javatachteam_hakaton.entity.User;
import com.jtt.javatachteam_hakaton.entity.enums.GradeEnum;
import com.jtt.javatachteam_hakaton.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

public class AuthService {
	private final UserRepository userRepository;

	public AuthService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public String login(String username, String rawPassword) {
		Optional<User> userOpt = userRepository.findByUsername(username);

		if (userOpt.isPresent()) {
			User user = userOpt.get();
			if (BCrypt.checkpw(rawPassword, user.getPassHash())) {
				return JwtProvider.generateToken(user.getId());
			}
		}
		return null;
	}

	public UUID loginWithCookie(String username, String rawPassword) {
		Optional<User> userOpt = userRepository.findByUsername(username);

		if (userOpt.isPresent()) {
			User user = userOpt.get();
			if (BCrypt.checkpw(rawPassword, user.getPassHash())) {
				return user.getId();
			}
		}
		return null;
	}

	public String signup(String username, String rawPassword, String firstname, String lastname) {
		if (userRepository.findByUsername(username).isPresent()) {
			throw new IllegalArgumentException("Пользователь с таким именем уже существует");
		}

		User user = new User();
		user.setUsername(username);
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setRole("USER");
		user.setGradeLevel(GradeEnum.Junior);
		user.setCreatedAt(Instant.now());

		String hashedPass = BCrypt.hashpw(rawPassword, BCrypt.gensalt());
		user.setPassHash(hashedPass);

		User savedUser = userRepository.save(user);

		return JwtProvider.generateToken(savedUser.getId());
	}

	public UUID signupWithCookie(String username, String rawPassword, String firstname, String lastname) {
		if (userRepository.findByUsername(username).isPresent()) {
			throw new IllegalArgumentException("Пользователь с таким именем уже существует");
		}

		User user = new User();
		user.setUsername(username);
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setRole("USER");
		user.setGradeLevel(GradeEnum.Junior);
		user.setCreatedAt(Instant.now());

		String hashedPass = BCrypt.hashpw(rawPassword, BCrypt.gensalt());
		user.setPassHash(hashedPass);

		User savedUser = userRepository.save(user);

		return savedUser.getId();
	}
}