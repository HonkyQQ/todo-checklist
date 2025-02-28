package com.example.todo_checklist.controller;

import com.example.todo_checklist.model.Task;
import com.example.todo_checklist.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/add")
    public String addTask(@RequestBody Task task) {
        // Если дедлайн не передан, оставляем его null
        if (task.getDeadline() == null) {
            task.setDeadline(null);
        }

        // Если статус не передан, устанавливаем его в false
        if (task.getCompleted() == null) {
            task.setCompleted(false);
        }

        taskService.addTask(task);
        return "Задача добавлена: " + task;
    }

    @PutMapping("/{taskNumber}/status")
    public ResponseEntity<String> updateTaskStatus(@PathVariable int taskNumber, @RequestParam boolean completed) {
        boolean updated = taskService.updateTaskStatus(taskNumber, completed);

        if (updated) {
            return ResponseEntity.ok("Статус задачи #" + taskNumber + " обновлён!");
        } else {
            return ResponseEntity.badRequest().body("Задача #" + taskNumber + " не найдена!");
        }
    }
}