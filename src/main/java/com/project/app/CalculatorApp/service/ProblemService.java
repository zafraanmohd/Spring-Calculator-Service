package com.project.app.CalculatorApp.service;

import com.project.app.CalculatorApp.domain.Problem;
import com.project.app.CalculatorApp.domain.User;

public interface ProblemService {
    Problem createRandomProblem(User user);
}