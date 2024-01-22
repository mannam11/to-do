package com.todo.remainder.service;

import com.todo.remainder.entity.Task;
import com.todo.remainder.entity.TaskStatus;
import com.todo.remainder.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {

    private TaskRepository taskRepository;

    public void createTask(Task task){
        task.setTaskStatus(TaskStatus.INPROGRESS);
        taskRepository.save(task);
    }

    public List<Task> findAllInProgress(TaskStatus taskStatus){
        return taskRepository.findByTaskStatus(taskStatus);
    }

    public List<Task> findAllCompleted(TaskStatus taskStatus){
        return taskRepository.findByTaskStatus(taskStatus);
    }

//    public void markTaskAsCompleted(int taskId) {
//        Optional<Task> optionalTask = taskRepository.findById(taskId);
//        optionalTask.ifPresent(task -> {
//            task.setTaskStatus(TaskStatus.COMPLETED);
//            taskRepository.save(task);
//        });
//    }

    public Optional<Task> findTaskById(int taskId) {
        return taskRepository.findById(taskId);
    }

    public void save(Task task){
        taskRepository.save(task);
    }
}
