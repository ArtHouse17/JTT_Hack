package com.jtt.javatachteam_hakaton.api.dto;

public record ProgressDto(
    int pointsTotal,
    int pointsEarned,
    int testTasksTotal,
    int testTasksSolved,
    int mistakesTasksTotal,
    int mistakesTasksSolved,
    int openTasksTotal,
    int openTasksSolved
) {}
