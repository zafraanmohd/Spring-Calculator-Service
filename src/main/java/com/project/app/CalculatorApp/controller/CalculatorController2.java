package com.project.app.CalculatorApp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.project.app.CalculatorApp.domain.Attempt;
import com.project.app.CalculatorApp.domain.Problem;
import com.project.app.CalculatorApp.domain.User;
import com.project.app.CalculatorApp.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CalculatorController2 {
    private UserService userService;
    private HttpSession httpSession;

    public CalculatorController2(UserService userService, HttpSession httpSession) {
        this.userService = userService;
        this.httpSession = httpSession;
    }

    @GetMapping
    public String login() {
        return "login";
    }

    @PostMapping("login/")
    public String loginProcess(@RequestParam("name") String name, Model model) {
        User user;
        try {
            user = userService.getUser(name);
            System.out.println(user.getName());
            model.addAttribute("user", user);
            model.addAttribute("problems", userService.getAllUserProblems(user));
            System.out.println(user.getName() + ": in");
        } catch (Exception e) {
            System.out.println(name + ": Not Found");
            return "error";
        }
        httpSession.setAttribute("name", name);
        return "home";
    }

    @PostMapping("register/")
    public String registerProcess(@RequestParam("name") String name, Model model) {
        User user;
        try {
            user = userService.createUser(name);
            System.out.println(name + ": Registered");
            model.addAttribute("user", user);
            model.addAttribute("problem", null);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        httpSession.setAttribute("name", name);
        return "home";
    }

    @GetMapping("home/")
    public String home(Model model) {
        User user;
        try {
            user = userService.getUser((String) httpSession.getAttribute("name"));
            model.addAttribute("user", user);
            model.addAttribute("problems", userService.getAllUserProblems(user));
        } catch (Exception e) {
            return "error";
        }
        return "home";
    }

    @GetMapping("createProblem/")
    public String createProblem(Model model) {
        User user = userService.getUser((String) httpSession.getAttribute("name"));
        Problem problem = userService.addProblem(user);
        model.addAttribute("message", "Try");
        model.addAttribute("problem", problem);
        httpSession.setAttribute("problem", problem.getId());
        return "problem";
    }

    @PostMapping("createAttempt/")
    public String attemptProblem(@RequestParam("attempt") String attempt, Model model) {
        Problem problem = userService.getProblem((Long) httpSession.getAttribute("problem"));
        System.out.println(attempt);
        boolean finalResult = userService.addAttempt(problem, (new Integer(attempt).intValue()));
        System.out.println(finalResult);
        if (!finalResult) {
            model.addAttribute("message", "try again!");
            model.addAttribute("problem", problem);
            return "problem";
        }
        return "correct";
    }

    @GetMapping("leaderboard/")
    @ResponseBody
    public List<?> getLeaderBoard(Model model) {
        List<?> attempts = userService.getAllProblemsByAll();
        return attempts;
    }
}