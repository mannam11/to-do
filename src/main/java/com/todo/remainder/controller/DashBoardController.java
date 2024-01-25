package com.todo.remainder.controller;

import com.todo.remainder.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class DashBoardController {

    private TaskService taskService;

    @GetMapping("/")
    public String getDashboardPage(Model model){

        return "dashboard";
    }


}
