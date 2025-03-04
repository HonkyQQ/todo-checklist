package com.example.todo_checklist.service;

import com.example.todo_checklist.model.Task;

public interface TaskService {
    void addTask(Task task);
    boolean updateTaskStatus(int taskNumber, boolean completed); // Теперь возвращает boolean
}