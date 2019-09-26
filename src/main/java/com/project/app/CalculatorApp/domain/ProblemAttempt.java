package com.project.app.CalculatorApp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ProblemAttempt {
    @Id
    @GeneratedValue
    private Long id;
    private Integer score;
    private boolean result;

    @ManyToOne
    @JsonBackReference
    private Problem problem;

    public ProblemAttempt(Integer score, boolean result) {
        this.score = score;
        this.result = result;
    }
}