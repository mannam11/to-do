package com.todo.remainder.service;

import com.todo.remainder.entity.Task;
import com.todo.remainder.entity.TaskStatus;
import com.todo.remainder.entity.User;
import com.todo.remainder.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {

    private TaskRepository taskRepository;

    private UserService userService;

    public void createTask(Task task){
        task.setTaskStatus(TaskStatus.INPROGRESS);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null  && authentication.isAuthenticated()){
            String username = authentication.getName();

            User taskCreatingUser = userService.findUser(username);
            task.setUser(taskCreatingUser);
        }

        taskRepository.save(task);
    }

    public List<Task> findAllInProgress(TaskStatus taskStatus){
        return taskRepository.findByTaskStatus(taskStatus);
    }

    public List<Task> findAllCompleted(TaskStatus taskStatus){
        return taskRepository.findByTaskStatus(taskStatus);
    }

    public Optional<Task> findTaskById(int taskId) {
        return taskRepository.findById(taskId);
    }

    public void save(Task task){
        taskRepository.save(task);
    }

    public void deleteTask(Task task){
        taskRepository.delete(task);
    }
}
