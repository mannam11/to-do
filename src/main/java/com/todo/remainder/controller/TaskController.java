package com.todo.remainder.controller;


import com.todo.remainder.entity.Task;
import com.todo.remainder.entity.TaskStatus;
import com.todo.remainder.service.PriorityService;
import com.todo.remainder.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class TaskController {

    private final TaskService taskService;

    private final PriorityService priorityService;

    @Autowired
    public TaskController(TaskService taskService, PriorityService priorityService) {
        this.taskService = taskService;
        this.priorityService = priorityService;
    }

    @GetMapping("/create")
    public String getTaskCreationPage(Model model){
        model.addAttribute("task", new Task());
        model.addAttribute("priorities", priorityService.findAll());
        return "create_task";
    }

    @PostMapping("/create")
    public String createMyTask(@ModelAttribute Task task, Model model) {

        if(task.getToBeComplete() == null){
            task.setToBeComplete(LocalDate.now());
        }
        if(taskService.isValidTask(task)){
            taskService.createTask(task);
            return "redirect:/inprogress";
        }else{
            model.addAttribute("taskError","Please fill all the fields correctly");
            model.addAttribute("priorities", priorityService.findAll());
            return "create_task";
        }
    }

    @GetMapping("/edit/{taskId}")
    public String getEditPage(@PathVariable("taskId") int taskId,Model model){
        Optional<Task> task = taskService.findTaskById(taskId);
        if(task.isPresent()){
            Task targetTask = task.get();
            model.addAttribute("task",targetTask);
            model.addAttribute("priorities", priorityService.findAll());
            return "create_task";
        }

        return "redirect:/error";
    }

    @PostMapping("/edit/{taskId}")
    public String updateTask(@PathVariable("taskId") int taskId, @ModelAttribute Task updatedTask, Model model) {
        Optional<Task> existingTaskOptional = taskService.findTaskById(taskId);
        if (existingTaskOptional.isPresent()) {
            Task existingTask = existingTaskOptional.get();
            existingTask.setTitle(updatedTask.getTitle());
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setToBeComplete(updatedTask.getToBeComplete());
            existingTask.setPriority(updatedTask.getPriority());

            taskService.save(existingTask);
            return "redirect:/inprogress";
        } else {
            // Handle case where task with given ID is not found
            model.addAttribute("error", "Task not found");
            return "error"; // You need to have an error page configured
        }
    }


    @GetMapping("/inprogress")
    public String getTasksInProgress(Model model){
        List<Task> tasks = taskService.findByStatus(TaskStatus.INPROGRESS);
        model.addAttribute("progress", tasks);
        model.addAttribute("currentPage", "inprogress");
        return "inprogress";
    }

    @GetMapping("/completed")
    public String getCompletedTasks(Model model){
        List<Task> tasks = taskService.findByStatus(TaskStatus.COMPLETED);
        model.addAttribute("completed", tasks);
        model.addAttribute("currentPage", "completed");
        return "completed";
    }

    @PostMapping("/toggle/{taskId}")
    public String toggleTaskStatus(@PathVariable int taskId) {
        Optional<Task> optionalTask = taskService.findTaskById(taskId);

        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();

            if (TaskStatus.COMPLETED.equals(task.getTaskStatus())) {
                // If the task is completed, mark it as in progress
                task.setTaskStatus(TaskStatus.INPROGRESS);
                taskService.save(task);
                return "redirect:/completed";
            } else {
                // If the task is in progress, mark it as completed
                task.setTaskStatus(TaskStatus.COMPLETED);
                taskService.save(task);
                return "redirect:/inprogress";
            }
        }

        return "redirect:/error";
    }

    @PostMapping("/delete/{taskId}")
    public String deleteTask(@PathVariable int taskId, @ModelAttribute("currentPage") String currentPage) {
        Optional<Task> optionalTask = taskService.findTaskById(taskId);
        System.out.println("Deleting task with ID: " + taskId);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            TaskStatus status = task.getTaskStatus();
            taskService.deleteTask(task);

            if(status.equals(TaskStatus.INPROGRESS)){
                return "redirect:/inprogress";
            }else{
                return "redirect:/completed";
            }

        }

        return "redirect:/error";
    }

}
