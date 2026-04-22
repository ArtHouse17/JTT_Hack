package com.jtt.javatachteam_hakaton.api.service;

import java.util.List;
import java.util.Map;

public class StubApiService {
//МОКОВЫЕ ЗАТЫЧКИ ДЛЯ ЗАПРОСОВ, КАК DTO появятся все будет переделано

    public Map<String, Object> login() {
        return Map.of("ok", true);
    }

    public Map<String, Object> logout() {
        return Map.of("ok", true);
    }

    public Map<String, Object> signup() {
        return Map.of("ok", true);
    }

    public Map<String, Object> currentUser() {
        return Map.of("username", "HAKATON");
    }

    public Map<String, Object> currentUserProgress() {
        return Map.of("ok", true);
    }

    public Map<String, Object> resetCurrentUserProgress() {
        return Map.of("ok", true);
    }

    public List<Object> tasks() {
        return List.of();
    }

    public Map<String, Object> taskById(String taskId) {
        return Map.of(
            "id", taskId,
            "ok", true
        );
    }

    public Map<String, Object> submitTaskAttempt(String taskId) {
        return Map.of(
            "taskId", taskId,
            "correct", true
        );
    }

    public Map<String, Object> health() {
        return Map.of("status", "ok");
    }
}
