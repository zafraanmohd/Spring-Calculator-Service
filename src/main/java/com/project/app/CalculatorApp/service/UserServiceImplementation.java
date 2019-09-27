package com.project.app.CalculatorApp.service;

import java.util.List;

import com.project.app.CalculatorApp.domain.Attempt;
import com.project.app.CalculatorApp.domain.Problem;
import com.project.app.CalculatorApp.domain.User;
import com.project.app.CalculatorApp.domain.Repository.AttemptRepository;
import com.project.app.CalculatorApp.domain.Repository.ProblemRepository;
import com.project.app.CalculatorApp.domain.Repository.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {
    UserRepository userRepo;
    ProblemRepository problemRepo;
    AttemptRepository attemptRepo;
    FactorGenerationServiceImplementation randomGenerator;

    public UserServiceImplementation(UserRepository userRepo, ProblemRepository problemRepo,
            AttemptRepository attemptRepo, FactorGenerationServiceImplementation randomGenerator) {
        this.userRepo = userRepo;
        this.problemRepo = problemRepo;
        this.attemptRepo = attemptRepo;
        this.randomGenerator = randomGenerator;
    }

    @Override
    public User createUser(String name) {
        User user = new User(name);
        return userRepo.save(user);
    }

    @Override
    public Problem addProblem(User user) {
        Problem problem = new Problem(user, randomGenerator.createRandomFactor(), randomGenerator.createRandomFactor());
        return problemRepo.save(problem);
    }

    @Override
    public boolean addAttempt(Problem problem, int attempt) {
        Attempt attempt_ = new Attempt(problem, attempt);
        attemptRepo.save(attempt_);
        return attempt_.getResult();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public List<Problem> getAllUserProblems(User user) {
        if (userRepo.existsById(user.getId()))
            return problemRepo.findByUserId(user.getId());
        return null;
    }

    @Override
    public List<Attempt> getAllAttempts(Problem problem) {
        if (problemRepo.existsById(problem.getId()))
            return attemptRepo.findAllByProblemId(problem.getId());
        return null;
    }

    @Override
    public User getUser(Long user_id) {
        if (userRepo.existsById(user_id))
            return userRepo.findById(user_id).get();
        return null;
    }
}