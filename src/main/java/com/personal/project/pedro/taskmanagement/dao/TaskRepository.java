package com.personal.project.pedro.taskmanagement.dao;

import com.personal.project.pedro.taskmanagement.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUserId(Long userId);

    void deleteByUserId(Long userId);
}
