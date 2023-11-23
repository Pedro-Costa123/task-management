package com.personal.project.pedro.taskmanagement.dao;

import com.personal.project.pedro.taskmanagement.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
