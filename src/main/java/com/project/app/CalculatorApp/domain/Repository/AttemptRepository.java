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
    @Query("select a from Attempt a where a.problem.id=:p_id")
    List<Attempt> findAllByProblemId(@Param("p_id") Long p_id);

    @Query("SELECT a FROM Attempt a WHERE a.problem.id=:p_id")
    List<Attempt> testQuery(@Param("p_id") Long p_id);
}