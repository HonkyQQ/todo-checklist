package com.example.todo_checklist.service;

import com.example.todo_checklist.model.Task;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Service
public class SimpleTaskService implements TaskService {
    private final List<Task> tasks = new ArrayList<>();
    private static final String FILE_PATH = "tasks/todo-list.txt";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Override
    public void addTask(Task task) {
        tasks.add(task);
        saveTaskToFile(task);
        System.out.println("Задача добавлена: " + task);
    }
    @Override
    public boolean updateTaskStatus(int taskNumber, boolean completed) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));

            if (taskNumber < 1 || taskNumber > lines.size()) {
                System.out.println("Ошибка: Номер задачи вне диапазона!");
                return false; // Возвращаем false при ошибке
            }

            List<String> updatedLines = new ArrayList<>();
            for (int i = 0; i < lines.size(); i++) {
                if (i == taskNumber - 1) {
                    String[] parts = lines.get(i).split(" \\| ");
                    if (parts.length >= 3) {
                        parts[1] = completed ? "выполнено" : "не выполнено";
                        updatedLines.add(String.join(" | ", parts));
                    }
                } else {
                    updatedLines.add(lines.get(i));
                }
            }

            Files.write(Paths.get(FILE_PATH), updatedLines);
            System.out.println("Задача №" + taskNumber + " обновлена: " + (completed ? "выполнено" : "не выполнено"));
            return true; // Возвращаем true при успешном обновлении

        } catch (IOException e) {
            System.err.println("Ошибка при обновлении файла: " + e.getMessage());
            return false; // Возвращаем false в случае ошибки
        }
    }

    private void saveTaskToFile(Task task) {
        try {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                String formattedTask = formatTask(task, tasks.size());
                writer.write(formattedTask + "\n");
            }
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    private String formatTask(Task task, int taskNumber) {
        String status = task.isCompleted() ? "выполнено" : "не выполнено";
        String deadline = (task.getDeadline() != null) ? task.getDeadline().format(DATE_FORMAT) : "без дедлайна";
        return taskNumber + ". " + task.getDescription() + " | " + status + " | дедлайн: " + deadline;
    }
}