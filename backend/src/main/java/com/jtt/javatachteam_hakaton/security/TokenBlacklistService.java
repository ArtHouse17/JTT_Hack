package com.jtt.javatachteam_hakaton.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TokenBlacklistService {
    private static final Logger logger = LoggerFactory.getLogger(TokenBlacklistService.class);

    private final Map<String, Date> blacklistedTokens = new ConcurrentHashMap<>();
    private final ScheduledExecutorService cleaner = Executors.newSingleThreadScheduledExecutor();

    public TokenBlacklistService() {
        cleaner.scheduleAtFixedRate(this::cleanExpiredTokens, 30, 30, TimeUnit.MINUTES);
        logger.info("TokenBlacklistService инициализирован");
    }

    public void blacklist(String token) {
        try {
            Date expiration = JwtProvider.getExpirationDate(token);
            Date now = new Date();

            if (expiration.after(now)) {
                blacklistedTokens.put(token, expiration);
                logger.info("Токен занесен в черный список, срок его действия истекает в: {}", expiration);

                long ttl = expiration.getTime() - now.getTime();
                cleaner.schedule(() -> {
                    blacklistedTokens.remove(token);
                    logger.debug("Токен удален из черного списка после истечения срока действия");
                }, ttl, TimeUnit.MILLISECONDS);
            }
        } catch (Exception e) {
            logger.error("Не удалось внести токен в черный список: {}", e.getMessage());
            throw new IllegalArgumentException("Неверный формат токена");
        }
    }

    public boolean isBlacklisted(String token) {
        return blacklistedTokens.containsKey(token);
    }

    private void cleanExpiredTokens() {
        Date now = new Date();
        int beforeSize = blacklistedTokens.size();
        blacklistedTokens.entrySet().removeIf(entry -> entry.getValue().before(now));
        int afterSize = blacklistedTokens.size();

        if (beforeSize != afterSize) {
            logger.info("Удалены просроченные токены {} из черного списка", beforeSize - afterSize);
        }
    }

    public int getBlacklistSize() {
        return blacklistedTokens.size();
    }

    public void clear() {
        blacklistedTokens.clear();
        logger.warn("Черный список токенов очищен");
    }
}