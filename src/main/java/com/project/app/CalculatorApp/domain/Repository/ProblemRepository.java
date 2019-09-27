package com.project.app.CalculatorApp.domain.Repository;

import java.util.List;

import com.project.app.CalculatorApp.domain.Problem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProblemRepository extends JpaRepository<Problem, Long> {
    @Query("select p from Problem p where p.user.id=:user_id")
    List<Problem> findByUserId(@Param("user_id") Long user_id);
}