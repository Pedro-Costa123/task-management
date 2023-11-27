package com.personal.project.pedro.taskmanagement.services;

import com.personal.project.pedro.taskmanagement.dao.TaskRepository;
import com.personal.project.pedro.taskmanagement.dao.UserRepository;
import com.personal.project.pedro.taskmanagement.entities.Task;
import com.personal.project.pedro.taskmanagement.enums.Status;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        return task.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found with id " + id));
    }

    public List<Task> getTasksByUserId(Long userId) {
        if (userRepository.findById(userId).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id " + userId + " to get tasks");
        }
        return taskRepository.findByUserId(userId);
    }

    public Task createTask(Task task) {
        Long userId = task.getUserId();
        if (userRepository.findById(userId).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id " + userId + " to create task");
        }
        return taskRepository.save(task);
    }

    public List<Task> getTasksByStatusAndUserId(String status, Long userId) {
        if (userRepository.findById(userId).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id " + userId + " to get tasks");
        }
        for (Status checkStatus : Status.values()) {
            if (checkStatus.getValue().equals(status.toUpperCase())) {
                return taskRepository.findByStatusAndUserId(status.toUpperCase(), userId);
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status " + status + " is not valid, needs to be one of: " + Arrays.toString(Status.values()) + " to get tasks by status and user id");
    }

    public void deleteTask(Long id) {
        if (taskRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found with id " + id + " to delete");
        }
        taskRepository.deleteById(id);
    }

    public int deleteAllUserTasks(Long userId) {
        if (userRepository.findById(userId).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id " + userId + " to delete tasks");
        }
        int tasksDeleted = taskRepository.findByUserId(userId).size();
        taskRepository.deleteByUserId(userId);
        return tasksDeleted;
    }

    public Task updateTask(Task task) {
        Long taskId = task.getTaskId();
        if (taskRepository.findById(taskId).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found with id " + taskId + " to update");
        }
        if (userRepository.findById(task.getUserId()).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id " + task.getUserId() + " to update task");
        }
        return taskRepository.save(task);
    }

    public Task updateTaskStatus(Long taskId, String status) {
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found with id " + taskId + " to update");
        }
        for (Status checkStatus : Status.values()) {
            if (checkStatus.getValue().equals(status.toUpperCase())) {
                task.get().setStatus(status.toUpperCase());
                return taskRepository.save(task.get());
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status " + status + " is not valid, needs to be one of: " + Arrays.toString(Status.values()) + " to update task status");
    }

    public Task updateTaskDueDate(Long taskId, int days) {
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found with id " + taskId + " to update");
        }
        if (days < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Days " + days + " is not valid, needs to be a positive number to update task due date");
        }
        LocalDate date = LocalDate.parse(task.get().getDueDate());
        date = date.plusDays(days);
        task.get().setDueDate(date.toString());
        return taskRepository.save(task.get());

    }

}
