package com.basic.auth.services.impl;

import com.basic.auth.models.task.Status;
import com.basic.auth.models.task.Task;
import com.basic.auth.repositories.TaskRepository;
import com.basic.auth.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Override
    public List<Task> getTasksByUserId(Long userId) {
        return taskRepository.findAllByUserId(userId);
    }

    @Override
    public Task update(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task changeStatus(Long id, Status status) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setStatus(status);
                    return taskRepository.save(task);
                })
                .orElse(null);
    }

    @Override
    public Task create(Task task, Long userId) {
        return taskRepository.save(task);
    }

    @Override
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
