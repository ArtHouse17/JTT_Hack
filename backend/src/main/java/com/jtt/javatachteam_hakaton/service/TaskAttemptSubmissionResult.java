package com.jtt.javatachteam_hakaton.service;

import com.jtt.javatachteam_hakaton.entity.Attempt;

public record TaskAttemptSubmissionResult(
    Attempt attempt,
    String errorCode,
    String errorMessage
) {
}
