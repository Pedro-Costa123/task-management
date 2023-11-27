package com.personal.project.pedro.taskmanagement.controllers;

import com.personal.project.pedro.taskmanagement.entities.Task;
import com.personal.project.pedro.taskmanagement.services.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/all")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/getById")
    public Task getTaskById(@RequestParam Long id) {
        return taskService.getTaskById(id);
    }

    @GetMapping("/getByUserId")
    public List<Task> getTasksByUserId(@RequestParam Long userId) {
        return taskService.getTasksByUserId(userId);
    }

    @GetMapping("/getByStatusAndUserId")
    public List<Task> getTasksByStatusAndUserId(@RequestParam String status, @RequestParam Long userId) {
        return taskService.getTasksByStatusAndUserId(status, userId);
    }

    @PostMapping("/create")
    public String createTask(@RequestBody Task task) {
        Task taskCreated = taskService.createTask(task);
        return "Task created with id: " + taskCreated.getTaskId();
    }

    @DeleteMapping("/delete")
    public String deleteTask(@RequestParam Long id) {
        taskService.deleteTask(id);
        return "Task deleted with id: " + id;
    }

    @DeleteMapping("/deleteAllUserTasks")
    public String deleteAllUserTasks(@RequestParam Long userId) {
        int tasksDeleted = taskService.deleteAllUserTasks(userId);
        return "Number of tasks deleted: " + tasksDeleted;
    }

    @PutMapping("/update")
    public String updateTask(@RequestBody Task task) {
        Task taskUpdated = taskService.updateTask(task);
        return "Task updated with id: " + taskUpdated.getTaskId();
    }

    @PutMapping("/updateTaskStatus")
    public String updateTaskStatus(@RequestParam Long id, @RequestParam String status) {
        Task taskUpdated = taskService.updateTaskStatus(id, status);
        return "Task updated with id: " + taskUpdated.getTaskId();
    }

    @PutMapping("/updateTaskDueDate")
    public String updateTaskDueDate(@RequestParam Long id, @RequestParam int days) {
        Task taskUpdated = taskService.updateTaskDueDate(id, days);
        return "Task updated with id: " + taskUpdated.getTaskId();
    }
}
