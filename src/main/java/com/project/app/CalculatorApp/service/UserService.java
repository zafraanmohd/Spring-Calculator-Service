package com.project.app.CalculatorApp.service;

import com.project.app.CalculatorApp.domain.Problem;
import com.project.app.CalculatorApp.domain.User;

import java.util.List;

import com.project.app.CalculatorApp.domain.Attempt;;

/**
 * UserService
 */
public interface UserService {
    User createUser(String name);

    User getUser(String name);

    User getUser(Long user_id);

    Problem addProblem(User user);

    Problem getProblem(Long pid);

    boolean addAttempt(Problem problem, int attempt);

    List<User> getAllUsers();

    List<Problem> getAllUserProblems(User user);

    List<Attempt> getAllAttempts(Problem problem);

    List<?> getAllProblemsByAll();
}