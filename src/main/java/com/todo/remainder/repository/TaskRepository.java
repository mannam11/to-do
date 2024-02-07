package com.todo.remainder.repository;

import com.todo.remainder.entity.Task;
import com.todo.remainder.entity.TaskStatus;
import com.todo.remainder.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByTaskStatusAndUser(TaskStatus taskStatus, User user);
}