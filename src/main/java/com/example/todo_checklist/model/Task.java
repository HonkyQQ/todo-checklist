package com.example.todo_checklist.model;

import java.time.LocalDate;

public class Task {
    private String description;
    private Boolean completed; // Объектный тип Boolean, чтобы поддерживать null
    private LocalDate deadline;

    // Конструктор
    public Task(String description) {
        this.description = description;
        this.completed = false; // По умолчанию задача не выполнена
    }

    // Геттеры и сеттеры
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getCompleted() { // Возвращаем Boolean
        return completed;
    }

    public void setCompleted(Boolean completed) { // Устанавливаем значение Boolean
        this.completed = completed;
    }

    // Дополнительный метод isCompleted
    public boolean isCompleted() {
        return completed != null && completed; // Возвращаем примитивный boolean
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "Task{" +
                "description='" + description + '\'' +
                ", completed=" + completed +
                ", deadline=" + deadline +
                '}';
    }
}