package com.project.app.CalculatorApp.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Problem {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "factor_a")
    private Integer factorA;
    @Column(name = "factor_b")
    private Integer factorB;
    private Integer result;

    @ManyToOne
    @JsonBackReference
    private User user;

    @OneToMany
    @JsonManagedReference
    private List<ProblemAttempt> attempts;

    public Problem(Integer factorA, Integer factorB) {
        this.factorA = factorA;
        this.factorB = factorB;
        result = factorA * factorB;
        attempts = new ArrayList<>();
    }

    public boolean checkResult(Integer attempt) {
        return this.result.equals(attempt);
    }

    public boolean doAttempt(Integer answer) {
        attempts.add(new ProblemAttempt(100, checkResult(answer)));
        return checkResult(answer);
    }

}