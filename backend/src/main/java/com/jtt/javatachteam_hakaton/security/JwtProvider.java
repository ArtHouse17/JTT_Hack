package com.jtt.javatachteam_hakaton.security;

import com.jtt.javatachteam_hakaton.config.AppConfig;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

/**
 * Провайдер для работы с JWT токенами
 * Отвечает за генерацию, валидацию и извлечение данных из токенов
 */
public class JwtProvider {
	private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

	private static final String SECRET;
	private static final byte[] SECRET_BYTES;
	private static final long EXPIRATION_TIME = 86400000; // 24 часа в миллисекундах

	static {
		SECRET = AppConfig.fromEnvironment().SECRET();
		SECRET_BYTES = SECRET.getBytes(StandardCharsets.UTF_8);
		logger.info("JwtProvider initialized with secret length: {} bytes", SECRET_BYTES.length);
	}

	/**
	 * Генерация JWT токена для пользователя
	 * @param userId ID пользователя
	 * @return JWT токен
	 */
	public static String generateToken(UUID userId) {
		String token = Jwts.builder()
				.setId(UUID.randomUUID().toString())  // Уникальный ID токена
				.setSubject(userId.toString())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS256, SECRET_BYTES)
				.compact();

		logger.debug("Generated token for user: {}", userId);
		return token;
	}

	/**
	 * Извлечение ID пользователя из токена
	 * @param token JWT токен
	 * @return UUID пользователя
	 * @throws ExpiredJwtException если токен истек
	 * @throws MalformedJwtException если токен имеет неправильный формат
	 * @throws SignatureException если подпись невалидна
	 */
	public static UUID extractUserId(String token) {
		String subject = parseToken(token).getSubject();
		return UUID.fromString(subject);
	}

	/**
	 * Парсинг и валидация JWT токена
	 * @param token JWT токен
	 * @return Claims из токена
	 * @throws JwtException если токен невалидный
	 */
	public static Claims parseToken(String token) {
		try {
			return Jwts.parser()
					.setSigningKey(SECRET_BYTES)
					.parseClaimsJws(token)
					.getBody();
		} catch (ExpiredJwtException e) {
			logger.warn("Token expired: {}", e.getMessage());
			throw e;
		} catch (MalformedJwtException e) {
			logger.warn("Malformed token: {}", e.getMessage());
			throw e;
		} catch (SignatureException e) {
			logger.warn("Invalid signature: {}", e.getMessage());
			throw e;
		} catch (JwtException e) {
			logger.warn("Invalid token: {}", e.getMessage());
			throw e;
		}
	}

	/**
	 * Проверка валидности токена (без выбрасывания исключений)
	 * @param token JWT токен
	 * @return true если токен валидный
	 */
	public static boolean isValidToken(String token) {
		try {
			Claims claims = parseToken(token);
			Date expiration = claims.getExpiration();
			boolean isValid = expiration.after(new Date());

			if (!isValid) {
				logger.debug("Token is expired");
			}
			return isValid;
		} catch (JwtException e) {
			logger.debug("Token validation failed: {}", e.getMessage());
			return false;
		}
	}

	/**
	 * Получить время истечения токена
	 */
	public static Date getExpirationDate(String token) {
		return parseToken(token).getExpiration();
	}

	/**
	 * Получить ID токена
	 */
	public static String getTokenId(String token) {
		String id = parseToken(token).getId();
		return id != null ? id : "";
	}

	/**
	 * Проверить, истек ли токен
	 */
	public static boolean isExpired(String token) {
		try {
			Date expiration = getExpirationDate(token);
			return expiration.before(new Date());
		} catch (JwtException e) {
			return true;
		}
	}
}