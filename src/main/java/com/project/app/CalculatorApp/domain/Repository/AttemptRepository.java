package com.project.app.CalculatorApp.domain.Repository;

import java.util.List;

import com.project.app.CalculatorApp.domain.Attempt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * AttemptRepository
 */
public interface AttemptRepository extends JpaRepository<Attempt, Long> {
    @Query(value = "select a.* from user u, problem p, attempt a where u.user_id=:user_id and u.user_id=p.user_id and p.problem_id=a.problem_id;", nativeQuery = true)
    List<Attempt> findAllAttempts(@Param("user_id") Long user_id);
}