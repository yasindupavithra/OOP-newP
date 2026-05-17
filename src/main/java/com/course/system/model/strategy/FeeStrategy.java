package com.course.system.model.strategy;

/**
 * IMPROVEMENT (Design Pattern): Strategy Pattern for fee calculation.
 * Instead of if/else in Registration.calculateFee(), we define a
 * pluggable strategy interface. New student types just add a new class.
 */
public interface FeeStrategy {
    double calculateFee(double baseFee);
    String getStrategyName();
}
