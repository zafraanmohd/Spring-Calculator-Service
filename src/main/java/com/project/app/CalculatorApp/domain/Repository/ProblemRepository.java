package com.project.app.CalculatorApp.domain.Repository;

import com.project.app.CalculatorApp.domain.Problem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepository extends JpaRepository<Problem, Long> {
}