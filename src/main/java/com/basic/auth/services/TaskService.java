package com.basic.auth.services;

import com.basic.auth.models.task.Status;
import com.basic.auth.models.task.Task;

import java.util.List;

public interface TaskService {

    Task getTaskById(Long id);
    List<Task> getTasksByUserId(Long userId);
    Task update(Task task);
    Task changeStatus(Long id, Status status);

    Task create(Task task, Long userId);
    void delete(Long id);
}
