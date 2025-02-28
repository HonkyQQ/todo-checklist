package com.example.todo_checklist.service;

import com.example.todo_checklist.model.Task;
import java.time.LocalDate;

public class DeadlineTaskDecorator extends TaskServiceDecorator {

    public DeadlineTaskDecorator(TaskService decoratedService) {
        super(decoratedService);
    }

    @Override
    public void addTask(Task task) {
        if (task.getDeadline() == null) {
            task.setDeadline(LocalDate.now().plusDays(7));
        }
        super.addTask(task);
        System.out.println("Добавлен дедлайн: " + task.getDeadline());
    }

    @Override
    public void updateTaskStatus(int taskNumber, boolean completed) {
        decoratedService.updateTaskStatus(taskNumber, completed);
    }
}