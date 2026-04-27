package com.jtt.javatachteam_hakaton.api.dto;

public record OpenTaskDto(
    String id,
    String type,
    int points,
    boolean code,
    boolean solved,
    String question,
    String answer
) {}
