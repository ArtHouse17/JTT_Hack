package com.jtt.javatachteam_hakaton.dao;

import com.jtt.javatachteam_hakaton.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskDAO extends JpaRepository<Task, UUID> {
}
