package com.jtt.javatachteam_hakaton.dao;

import com.jtt.javatachteam_hakaton.entity.AttemptAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttemptAnswersDAO extends JpaRepository<AttemptAnswer, UUID> {
}
