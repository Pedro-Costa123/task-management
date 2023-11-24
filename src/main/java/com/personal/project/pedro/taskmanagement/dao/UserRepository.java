package com.personal.project.pedro.taskmanagement.dao;

import com.personal.project.pedro.taskmanagement.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
