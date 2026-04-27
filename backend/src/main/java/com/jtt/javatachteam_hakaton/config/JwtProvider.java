package com.jtt.javatachteam_hakaton.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.UUID;

public class JwtProvider {
	private static final String SECRET = AppConfig.fromEnvironment().SECRET();
	private static final long EXPIRATION_TIME = 86400000; // 24 часа

	public static String generateToken(UUID userId) {
		return Jwts.builder()
				.setSubject(userId.toString())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS256, SECRET.getBytes())
				.compact();
	}

	public static UUID extractUserId(String token) {
		String subject = Jwts.parser()
				.setSigningKey(SECRET.getBytes())
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
		return UUID.fromString(subject);
	}
}