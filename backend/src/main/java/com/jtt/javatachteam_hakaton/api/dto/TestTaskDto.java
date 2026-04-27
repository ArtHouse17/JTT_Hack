package com.jtt.javatachteam_hakaton.api.dto;

import java.util.List;

public record TestTaskDto(
    String id,
    String type,
    int points,
    boolean multiple,
    boolean solved,
    String question,
    List<TaskOptionDto> options
) {}
