package com.project.app.CalculatorApp;

import java.util.Arrays;

import com.project.app.CalculatorApp.domain.Attempt;
import com.project.app.CalculatorApp.domain.Problem;
import com.project.app.CalculatorApp.domain.User;
import com.project.app.CalculatorApp.domain.Repository.AttemptRepository;
import com.project.app.CalculatorApp.domain.Repository.ProblemRepository;
import com.project.app.CalculatorApp.domain.Repository.UserRepository;
import com.project.app.CalculatorApp.service.UserService;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class CalculatorConfiguration extends WebSecurityConfigurerAdapter {
    private UserService userService;
    private AttemptRepository attemptRepo;
    ProblemRepository problemRepo;
    UserRepository userRepo;

    public CalculatorConfiguration(UserService userService, AttemptRepository attemptRepo, UserRepository userRepo,
            ProblemRepository problemRepo) {
        this.userService = userService;
        this.attemptRepo = attemptRepo;
        this.problemRepo = problemRepo;
        this.userRepo = userRepo;
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    }

    protected void configure(HttpSecurity http) throws Exception {
        // Enable H2 Console. Don't user in production
        http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/h2_console/**").permitAll();
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Bean
    public ApplicationRunner bootStrap() {
        return args -> {

            // User u1 = new User("Ant");
            // userRepo.save(u1);

            // Problem p1 = new Problem(u1, 2, 3);
            // problemRepo.save(p1);

            // Attempt a1 = new Attempt(p1, 5);
            // Attempt a2 = new Attempt(p1, 6);
            // attemptRepo.saveAll(Arrays.asList(a1, a2));

            User user = userService.createUser("Hobbs");
            Problem problem = userService.addProblem(user);
            System.out.println(userService.addAttempt(problem, 6));
            System.out.println(userService.addAttempt(problem, 7));
            System.out.println(userService.addAttempt(problem, 8));
            System.out.println(userService.addAttempt(problem, 9));
            System.out.println(userService.addAttempt(problem, 1));

            System.out.println(userService.getAllUsers().get(0).getName());
            System.out.println(userService.getAllUserProblems(user).get(0).getResult());
            System.out.println(userService.getAllAttempts(problem).get(0).getResult());
        };
    }
}