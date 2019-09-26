package com.project.app.CalculatorApp.controller;

import java.util.List;

import com.project.app.CalculatorApp.domain.Problem;
import com.project.app.CalculatorApp.domain.User;
import com.project.app.CalculatorApp.domain.Repository.ProblemRepository;
import com.project.app.CalculatorApp.domain.Repository.UserRepo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CalculatorController {

    private ProblemRepository problemRepo;
    private UserRepo userRepo;

    public CalculatorController(ProblemRepository problemRepo, UserRepo userRepo) {
        this.problemRepo = problemRepo;
        this.userRepo = userRepo;
    }

    @GetMapping
    @ResponseBody
    public String index() {
        return "This is index!";
    }

    @GetMapping("problems/all")
    @ResponseBody
    public List<Problem> allProblems() {
        return problemRepo.findAll();
    }

    @GetMapping("users/all")
    @ResponseBody
    public List<User> allUsers() {
        return userRepo.findAll();
    }
}