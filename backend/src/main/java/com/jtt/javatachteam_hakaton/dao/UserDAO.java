package com.jtt.javatachteam_hakaton.dao;

import com.jtt.javatachteam_hakaton.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserDAO extends JpaRepository<User, UUID> {
}
