package com.project.app.CalculatorApp.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;

@Getter
@Entity
public class Attempt {
    @Id
    @GeneratedValue
    @Column(name = "attempt_id")
    Long id;
    private int attempt;
    private boolean result;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "problem_id")
    private Problem problem;

    public Attempt(Problem problem, int attempt) {
        this.problem = problem;
        this.attempt = attempt;
        result = attempt == this.problem.getResult();
    }

    public Attempt() {
    }

    public boolean getResult() {
        return result;
    }
}