package com.project.app.CalculatorApp.domain.Repository;

import com.project.app.CalculatorApp.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

}