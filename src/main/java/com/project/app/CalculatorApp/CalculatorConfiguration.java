package com.project.app.CalculatorApp;

import java.util.Arrays;

import com.project.app.CalculatorApp.domain.Attempt;
import com.project.app.CalculatorApp.domain.Problem;
import com.project.app.CalculatorApp.domain.User;
import com.project.app.CalculatorApp.domain.Repository.AttemptRepository;

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
    private AttemptRepository attemptRepo;

    public CalculatorConfiguration(AttemptRepository attemptRepo) {
        this.attemptRepo = attemptRepo;
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    }

    protected void configure(HttpSecurity http) throws Exception {
        // Enale H2 Console. Don't user in production
        http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/h2_console/**").permitAll();
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Bean
    public ApplicationRunner bootStrap() {
        return args -> {
            User u1 = new User("Ant");
            Problem p1 = new Problem(u1, 2, 3);
            Attempt a1 = new Attempt(p1, 5);
            Attempt a2 = new Attempt(p1, 6);
            attemptRepo.saveAll(Arrays.asList(a1, a2));
            for (Attempt a : attemptRepo.findAllAttempts(u1.getId())) {
                System.out.println(a.getAttempt() + ": " + a.getResult());
            }

        };
    }
}