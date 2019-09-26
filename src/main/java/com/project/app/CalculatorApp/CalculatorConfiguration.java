package com.project.app.CalculatorApp;

import java.util.Arrays;

import com.project.app.CalculatorApp.domain.Problem;
import com.project.app.CalculatorApp.domain.User;
import com.project.app.CalculatorApp.domain.Repository.ProblemRepository;
import com.project.app.CalculatorApp.domain.Repository.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
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

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    }

    protected void configure(HttpSecurity http) throws Exception {
        // Enale H2 Console. Don't user in production
        http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/h2_console/**").permitAll();
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Autowired
    UserRepo userRepo;

    @Autowired
    ProblemRepository problemRepo;

    @Bean
    public ApplicationRunner bootStrap() {
        return args -> {
            User user = new User("Josh");
            User user1 = new User("Ant");

            System.out.println(user.getUsername());
            System.out.println(user1.getUsername());

            Problem problem = new Problem(2, 3);
            Problem problem2 = new Problem(1, 2);

            user.getProblems().add(problem);
            user.getProblems().add(problem2);

            problem.setUser(user);
            problem2.setUser(user);

            userRepo.saveAll(Arrays.asList(user, user1));

            System.out.println(problem.getResult());
            System.out.println(problem2.getResult());

            System.out.println(user.getProblems().get(0).doAttempt(5));
            System.out.println(user.getProblems().get(0).doAttempt(6));
        };
    }
}