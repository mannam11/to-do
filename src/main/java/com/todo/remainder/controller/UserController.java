package com.todo.remainder.controller;

import com.todo.remainder.entity.User;
import com.todo.remainder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/signup")
    public String getSignUpPage(Model model){
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String signUpAccount(@ModelAttribute User user, Model model){

        User requestedUser = userService.findUser(user.getEmail());

        if(requestedUser != null){
            model.addAttribute("emailExist", "Email already exists");
            return "signup";
        }

        if(user.getEmail().trim().length() == 0|| user.getPassword().trim().length() == 0){
            model.addAttribute("emailOrPasswordEmpty", "Email or Password should not be empty");
            return "signup";
        }

        if(user.getPassword().trim().length() < 7){
            model.addAttribute("passwordLength","Password at least have 7 characters ");
            return "signup";
        }

        String hashPassword  = passwordEncoder.encode(user.getPassword().trim());
        user.setPassword(hashPassword);
        userService.registerUser(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@ModelAttribute("user") User user){

        User requestedUser = userService.findUser(user.getEmail());

        if(requestedUser != null){
            if(passwordEncoder.matches(user.getPassword(), requestedUser.getPassword())){
                return "redirect:/";
            }
        }
        return "login?error=true";
    }

    @GetMapping("/error")
    public String getErrorPage(){
        return "error";
    }

}
