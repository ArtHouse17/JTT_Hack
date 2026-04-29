package com.jtt.javatachteam_hakaton.api.dto;

// Unified request DTO for /tasks/{taskId}/attempts:
// answer is either a String for text tasks or an array of option ids for test tasks.
public record TaskAttemptRequestDto(Object answer) {}
