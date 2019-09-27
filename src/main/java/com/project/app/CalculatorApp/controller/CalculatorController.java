package com.project.app.CalculatorApp.controller;

import java.util.List;

import com.project.app.CalculatorApp.domain.Attempt;
import com.project.app.CalculatorApp.domain.Problem;
import com.project.app.CalculatorApp.domain.User;
import com.project.app.CalculatorApp.domain.Repository.AttemptRepository;
import com.project.app.CalculatorApp.domain.Repository.ProblemRepository;
import com.project.app.CalculatorApp.domain.Repository.UserRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
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

    @GetMapping("users/{id}")
    public User getUser(@PathVariable("id") String id) {
        if (userRepo.existsById(new Long(id)))
            return userRepo.findById(new Long(id)).get();
        return null;
    }

    @GetMapping("users/{id}/problems")
    public List<Problem> getUserProblems(@PathVariable("id") String id) {
        return problemRepo.findByUserId(new Long(id));
    }

    @GetMapping("users/{uid}/problems/{pid}")
    public Problem getUserProblemById(@PathVariable("uid") String uid, @PathVariable("pid") String pid) {
        if (problemRepo.existsById(new Long(pid)))
            return problemRepo.findById(new Long(pid)).get();
        return null;
    }

    @GetMapping("users/{uid}/problems/{pid}/attempts")
    public List<Attempt> getUserProblemAttempts(@PathVariable("uid") String uid, @PathVariable("pid") String pid) {
        if (problemRepo.existsById(new Long(pid)))
            return attemptRepo.findAllByProblemId(new Long(pid));
        return null;
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