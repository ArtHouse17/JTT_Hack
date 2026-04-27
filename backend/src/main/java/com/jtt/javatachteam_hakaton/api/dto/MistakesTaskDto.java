package com.jtt.javatachteam_hakaton.api.dto;

public record MistakesTaskDto(
    String id,
    String type,
    int points,
    boolean solved,
    String question,
    String answer
) {}
