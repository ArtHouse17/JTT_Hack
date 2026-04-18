package com.jtt.javatachteam_hakaton.dao;

import com.jtt.javatachteam_hakaton.entity.TaskOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskOptionDAO extends JpaRepository<TaskOption, UUID> {
}
