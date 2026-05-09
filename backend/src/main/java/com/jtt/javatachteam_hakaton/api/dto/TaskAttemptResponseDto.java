package com.jtt.javatachteam_hakaton.api.dto;

public record TaskAttemptResponseDto(
    boolean correct,
    String errorCode,
    String errorMessage
) {}
