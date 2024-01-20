package com.todo.remainder.service;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.todo.remainder.entity.Task;
import com.todo.remainder.entity.User;
import com.todo.remainder.repository.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TaskService {

    private TaskRepository taskRepository;

    public void createTask(Task task){
        taskRepository.save(task);
    }

    public List<Task> findAll(){
        return taskRepository.findAll();
    }

}
