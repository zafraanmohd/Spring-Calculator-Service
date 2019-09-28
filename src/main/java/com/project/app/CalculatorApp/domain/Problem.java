package com.project.app.CalculatorApp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Problem {
    @Id
    @GeneratedValue
    @Column(name = "problem_id")
    Long id;
    private int factor_a;
    private int factor_b;
    private int result;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Problem(User user, int factor_a, int factor_b) {
        this.factor_a = factor_a;
        this.factor_b = factor_b;
        this.result = factor_a * factor_b;
        this.user = user;
    }
}