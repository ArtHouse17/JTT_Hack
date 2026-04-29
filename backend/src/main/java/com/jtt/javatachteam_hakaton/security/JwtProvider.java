package com.jtt.javatachteam_hakaton.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

public class JwtProvider {
	private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

	private static final SecretKey SECRET_KEY;
	private static final long EXPIRATION_TIME = 86400000; // 24 часа

	static {
		String secret = AppConfig.fromEnvironment().SECRET();
		// Для JJWT 0.12.6 нужно минимум 256 бит (32 байта)
		byte[] secretBytes = secret.getBytes(StandardCharsets.UTF_8);
		SECRET_KEY = Keys.hmacShaKeyFor(secretBytes);
		logger.info("JwtProvider initialized with secret key");
	}

	public static String generateToken(UUID userId) {
		return Jwts.builder()
				.id(UUID.randomUUID().toString())
				.subject(userId.toString())
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SECRET_KEY)
				.compact();
	}

	public static UUID extractUserId(String token) {
		String subject = parseToken(token).getSubject();
		return UUID.fromString(subject);
	}

	public static Claims parseToken(String token) {
		try {
			return Jwts.parser()
					.verifyWith(SECRET_KEY)
					.build()
					.parseSignedClaims(token)
					.getPayload();
		} catch (ExpiredJwtException e) {
			logger.warn("Token expired: {}", e.getMessage());
			throw e;
		} catch (MalformedJwtException e) {
			logger.warn("Malformed token: {}", e.getMessage());
			throw e;
		} catch (SecurityException e) {
			logger.warn("Invalid signature: {}", e.getMessage());
			throw e;
		} catch (JwtException e) {
			logger.warn("Invalid token: {}", e.getMessage());
			throw e;
		}
	}

	public static boolean isValidToken(String token) {
		try {
			Claims claims = parseToken(token);
			return claims.getExpiration().after(new Date());
		} catch (JwtException e) {
			return false;
		}
	}

	public static Date getExpirationDate(String token) {
		return parseToken(token).getExpiration();
	}

	public static boolean isExpired(String token) {
		try {
			return getExpirationDate(token).before(new Date());
		} catch (JwtException e) {
			return true;
		}
	}
}