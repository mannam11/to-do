package com.todo.remainder.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class DashBoardController {

    @GetMapping("/")
    public String getDashboardPage(){
        return "dashboard";
    }
}
