package com.basic.auth.repositories;

import com.basic.auth.models.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByUserId(Long userId);
//    void assignToUserById(Long taskId, Long userId);

}