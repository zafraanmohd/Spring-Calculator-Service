package com.project.app.CalculatorApp.service;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class FactorGenerationServiceImplementation implements FactorGenerationService {
    public int createRandomFactor() {
        Random random = new Random();
        return random.nextInt(100);
    }
}