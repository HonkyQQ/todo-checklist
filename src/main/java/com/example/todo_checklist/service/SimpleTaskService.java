package com.example.todo_checklist.service;

import com.example.todo_checklist.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SimpleTaskService implements TaskService {
    private final List<Task> tasks = new ArrayList<>();

    @Override
    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Задача добавлена: " + task);
    }
}