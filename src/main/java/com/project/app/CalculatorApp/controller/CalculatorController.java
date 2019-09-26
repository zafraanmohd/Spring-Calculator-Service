package com.project.app.CalculatorApp.controller;

import java.util.List;

import com.project.app.CalculatorApp.domain.Attempt;
import com.project.app.CalculatorApp.domain.Problem;
import com.project.app.CalculatorApp.domain.User;
import com.project.app.CalculatorApp.domain.Repository.AttemptRepository;
import com.project.app.CalculatorApp.domain.Repository.ProblemRepository;
import com.project.app.CalculatorApp.domain.Repository.UserRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {
    private UserRepository userRepo;
    private ProblemRepository problemRepo;
    private AttemptRepository attemptRepo;

    public CalculatorController(UserRepository userRepo, ProblemRepository problemRepo, AttemptRepository attemptRepo) {
        this.userRepo = userRepo;
        this.problemRepo = problemRepo;
        this.attemptRepo = attemptRepo;
    }

    @GetMapping("users/all")
    public List<User> allUsers() {
        return userRepo.findAll();
    }

    @GetMapping("problems/all")
    public List<Problem> allProblems() {
        return problemRepo.findAll();
    }

    @GetMapping("attempts/all")
    public List<Attempt> allAttempts() {
        return attemptRepo.findAll();
    }

}