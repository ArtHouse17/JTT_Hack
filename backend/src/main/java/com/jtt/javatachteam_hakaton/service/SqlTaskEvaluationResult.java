package com.jtt.javatachteam_hakaton.service;

public record SqlTaskEvaluationResult(
    boolean correct,
    String errorCode,
    String errorMessage
) {
    public static SqlTaskEvaluationResult success() {
        return new SqlTaskEvaluationResult(true, null, null);
    }

    public static SqlTaskEvaluationResult incorrect() {
        return new SqlTaskEvaluationResult(false, null, null);
    }

    public static SqlTaskEvaluationResult error(String errorCode, String errorMessage) {
        return new SqlTaskEvaluationResult(false, errorCode, errorMessage);
    }
}
