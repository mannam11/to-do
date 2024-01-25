package com.todo.remainder.controller;


import com.todo.remainder.entity.Task;
import com.todo.remainder.entity.TaskStatus;
import com.todo.remainder.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class TaskController {

    private TaskService taskService;

    @GetMapping("/create")
    public String getTaskCreationPage(Model model){
        model.addAttribute("task", new Task());
        return "create_task";
    }

    @PostMapping("/create")
    public String createMyTask(@ModelAttribute Task task, Model model) {

        if (task.getTitle() == null || task.getTitle().trim().isEmpty()) {
            model.addAttribute("titleError", "Title should not be empty");
            return "create_task";
        }

        if (task.getPriority() == null) {
            model.addAttribute("priorityError", "Priority should be selected");
            return "create_task";
        }

        if (task.getToBeComplete() != null) {
            try {
                // Set the time to the end of the day to avoid issues with time components
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(task.getToBeComplete());
                calendar.set(Calendar.HOUR_OF_DAY, 23);
                calendar.set(Calendar.MINUTE, 59);
                calendar.set(Calendar.SECOND, 59);
                task.setToBeComplete(calendar.getTime());

                // Check if the parsed date is in the past
                if (task.getToBeComplete().before(new Date())) {
                    model.addAttribute("dateError", "Deadline should be in the future");
                    model.addAttribute("task", task);
                    return "create_task";
                }
            } catch (Exception e) {
                // Handle invalid date format or other exceptions
                model.addAttribute("dateError", "Invalid date");
                model.addAttribute("task", task);
                return "create_task";
            }
        } else {
            // If the date is empty, set today's date
            task.setToBeComplete(new Date());
        }

        taskService.createTask(task);
        return "redirect:/inprogress";
    }

    @GetMapping("/inprogress")
    public String getTasksInProgress(Model model){
        List<Task> tasks = taskService.findAllInProgress(TaskStatus.INPROGRESS);
        model.addAttribute("progress", tasks);
        model.addAttribute("currentPage", "inprogress");
        return "inprogress";
    }

    @GetMapping("/completed")
    public String getCompletedTasks(Model model){
        List<Task> tasks = taskService.findAllCompleted(TaskStatus.COMPLETED);
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
