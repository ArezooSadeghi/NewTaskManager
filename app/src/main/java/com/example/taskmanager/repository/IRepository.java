package com.example.taskmanager.repository;

import com.example.taskmanager.model.Task;

import java.util.List;
import java.util.UUID;

public interface IRepository {
    List<Task> getCrimes();

    void insert(Task task);

    Task getCrime(UUID taskId);

    void update(Task task);

    void delete(Task task);

    int getPosition(UUID taskId);
}
