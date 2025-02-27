package com.example.todo_checklist.controller;

import com.example.todo_checklist.model.Task;
import com.example.todo_checklist.service.TaskService;
import com.example.todo_checklist.service.DeadlineTaskDecorator;
import com.example.todo_checklist.service.SimpleTaskService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController() {
        this.taskService = new DeadlineTaskDecorator(new SimpleTaskService());
    }

    @PostMapping("/add")
    public String addTask(@RequestBody String description) {
        Task task = new Task(description);
        taskService.addTask(task);
        return "Задача добавлена: " + task;
    }
}