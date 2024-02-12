package com.todo.remainder.controller;

import com.todo.remainder.entity.User;
import com.todo.remainder.service.EmailVerificationService;
import com.todo.remainder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserService userService;

    private final EmailVerificationService emailVerificationService;

    @Autowired
    public UserController(UserService userService, EmailVerificationService emailVerificationService) {
        this.userService = userService;
        this.emailVerificationService = emailVerificationService;
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

        userService.registerUser(user);

        emailVerificationService.sendVerificationEmail(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/error")
    public String getErrorPage(){
        return "error";
    }

    @GetMapping("/verify")
    public String verifyToken(@RequestParam("token") String token, Model model){

        boolean verified =emailVerificationService.verify(token) ;
        if(verified){
            model.addAttribute("tokenVerified","You have been successfully verified");
            return "/login";
        }else{
            return "/error";
        }
    }

}
