package com.jtt.javatachteam_hakaton.dao;

import com.jtt.javatachteam_hakaton.entity.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttemptDAO extends JpaRepository<Attempt, UUID> {
}
