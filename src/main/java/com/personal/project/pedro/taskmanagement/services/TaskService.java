package com.personal.project.pedro.taskmanagement.services;

import com.personal.project.pedro.taskmanagement.dao.TaskRepository;
import com.personal.project.pedro.taskmanagement.entities.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

}
