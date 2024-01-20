package com.todo.remainder.controller;


import com.todo.remainder.entity.Task;
import com.todo.remainder.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
        return "redirect:/tasks";
    }

    @GetMapping("/tasks")
    public String getTasks(Model model){
        List<Task> tasks = taskService.findAll();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

}
