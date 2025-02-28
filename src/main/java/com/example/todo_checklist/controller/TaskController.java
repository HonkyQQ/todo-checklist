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
        // Если дедлайн не передан, оставляем его null, иначе задаем его по умолчанию
        if (task.getDeadline() == null) {
            task.setDeadline(null); // Убедимся, что дедлайн не будет установлен по умолчанию
        }

        // Если статус не передан, устанавливаем его в false
        if (task.getCompleted() == null) {
            task.setCompleted(false); // Если статус не задан, по умолчанию задача не выполнена
        }

        taskService.addTask(task);
        return "Задача добавлена: " + task;
    }

    @PutMapping("/{taskNumber}/status")
    public ResponseEntity<String> updateTaskStatus(@PathVariable int taskNumber, @RequestParam boolean completed) {
        taskService.updateTaskStatus(taskNumber, completed);
        return ResponseEntity.ok("Статус задачи обновлён!");
    }
}