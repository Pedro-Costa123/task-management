package com.personal.project.pedro.taskmanagement.dao;

import com.personal.project.pedro.taskmanagement.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
