package com.todo.remainder.controller;

import com.todo.remainder.entity.User;
import com.todo.remainder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService ) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String getSignUpPage(Model model){
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String signUpAccount(@ModelAttribute User user, Model model){

        if(user.getEmail().trim().isEmpty()){
            model.addAttribute("error", "Password length should be at least 6");
            return "signup";
        }

        userService.registerUser(user);

        return "redirect:/dashboard";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@ModelAttribute("user") User user){
        try {
            System.out.println("User " + user);
            User requestedUser = userService.findUser(user.getEmail());

            if(requestedUser != null){
                System.out.println("Requested user " + requestedUser);
                return "redirect:/dashboard";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Return the logical view name without the leading slash
        return "login";
    }

    @GetMapping("/error")
    public String getErrorPage(){
        return "error";
    }

}
