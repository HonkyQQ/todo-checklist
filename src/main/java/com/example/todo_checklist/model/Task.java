package com.example.todo_checklist.model;

import java.time.LocalDate;

public class Task {
    private String description;
    private boolean completed;
    private LocalDate deadline;

    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    public LocalDate getDeadline() { return deadline; }
    public void setDeadline(LocalDate deadline) { this.deadline = deadline; }

    @Override
    public String toString() {
        return "Task{" +
                "description='" + description + '\'' +
                ", completed=" + completed +
                ", deadline=" + deadline +
                '}';
    }
}