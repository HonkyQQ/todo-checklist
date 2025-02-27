package com.example.todo_checklist.service;

import com.example.todo_checklist.model.Task;

public abstract class TaskServiceDecorator implements TaskService {
    protected final TaskService decoratedService;

    public TaskServiceDecorator(TaskService decoratedService) {
        this.decoratedService = decoratedService;
    }

    @Override
    public void addTask(Task task) {
        decoratedService.addTask(task);
    }
}