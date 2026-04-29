package com.jtt.javatachteam_hakaton.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Сервис для управления черным списком JWT токенов.
 * Хранит инвалидированные токены и автоматически очищает их после истечения.
 */
public class TokenBlacklistService {
    private static final Logger logger = LoggerFactory.getLogger(TokenBlacklistService.class);

    // Хранилище черного списка: token -> expirationTime
    private final Map<String, Date> blacklistedTokens = new ConcurrentHashMap<>();

    // Планировщик для очистки устаревших записей
    private final ScheduledExecutorService cleaner = Executors.newSingleThreadScheduledExecutor();

    public TokenBlacklistService() {
        // Запускаем очистку каждые 30 минут
        cleaner.scheduleAtFixedRate(this::cleanExpiredTokens, 30, 30, TimeUnit.MINUTES);
        logger.info("TokenBlacklistService initialized with automatic cleanup every 30 minutes");
    }

    /**
     * Добавить токен в черный список
     * @param token JWT токен
     * @throws IllegalArgumentException если токен невалидный
     */
    public void blacklist(String token) {
        try {
            Claims claims = JwtProvider.parseToken(token);
            Date expiration = claims.getExpiration();
            Date now = new Date();

            if (expiration.after(now)) {
                blacklistedTokens.put(token, expiration);
                logger.info("Token blacklisted, expires at: {}", expiration);

                // Автоматически удаляем токен из черного списка после истечения
                long ttl = expiration.getTime() - now.getTime();
                cleaner.schedule(() -> {
                    blacklistedTokens.remove(token);
                    logger.debug("Token automatically removed from blacklist after expiration");
                }, ttl, TimeUnit.MILLISECONDS);
            } else {
                logger.warn("Attempted to blacklist already expired token");
            }
        } catch (Exception e) {
            logger.error("Failed to blacklist token: {}", e.getMessage());
            throw new IllegalArgumentException("Invalid token format");
        }
    }

    /**
     * Проверить, находится ли токен в черном списке
     */
    public boolean isBlacklisted(String token) {
        boolean blacklisted = blacklistedTokens.containsKey(token);
        if (blacklisted) {
            logger.debug("Token found in blacklist");
        }
        return blacklisted;
    }

    /**
     * Очистить устаревшие записи (токены с истекшим сроком)
     */
    private void cleanExpiredTokens() {
        Date now = new Date();
        int beforeSize = blacklistedTokens.size();
        blacklistedTokens.entrySet().removeIf(entry -> entry.getValue().before(now));
        int afterSize = blacklistedTokens.size();

        if (beforeSize != afterSize) {
            logger.info("Cleaned {} expired tokens from blacklist. Current size: {}",
                    beforeSize - afterSize, afterSize);
        }
    }

    /**
     * Получить размер черного списка (для мониторинга)
     */
    public int getBlacklistSize() {
        return blacklistedTokens.size();
    }

    /**
     * Очистить весь черный список (для тестов)
     */
    public void clear() {
        blacklistedTokens.clear();
        logger.warn("Token blacklist cleared!");
    }
}