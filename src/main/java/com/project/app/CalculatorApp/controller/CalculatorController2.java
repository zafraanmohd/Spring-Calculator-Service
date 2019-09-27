package com.project.app.CalculatorApp.controller;

import com.project.app.CalculatorApp.domain.Problem;
import com.project.app.CalculatorApp.domain.User;
import com.project.app.CalculatorApp.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CalculatorController2 {
    private UserService userService;

    public CalculatorController2(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String login() {
        return "login";
    }

    @PostMapping("login/")
    public String loginProcess() {
        return "profile";
    }

    @GetMapping("user/{uid}/createProblem")
    @ResponseBody
    public Problem createProblem(User u) {
        User user = userService.getUser(u.getId());
        return userService.addProblem(user);
    }

    @GetMapping("user/{uid}/problem/{pid}/createAttempt")
    @ResponseBody
    public String attemptProblem(Problem p) {
        return Boolean.toString(userService.addAttempt(p, 5));
    }
}