package com.project.app.CalculatorApp.controller;

import com.project.app.CalculatorApp.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String createProblem() {
        return "quiz";
    }

    @GetMapping("user/{uid}/problem/{pid}/createAttempt")
    public String attemptProblem() {
        return "quiz";
    }
}