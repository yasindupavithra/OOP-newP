package com.course.system.model.strategy;

/**
 * IMPROVEMENT (Design Pattern): Concrete Strategy for international students.
 * International students pay 2.5x the base fee.
 */
public class InternationalFeeStrategy implements FeeStrategy {
    private static final double MULTIPLIER = 2.5;

    @Override
    public double calculateFee(double baseFee) {
        return baseFee * MULTIPLIER;
    }

    @Override
    public String getStrategyName() {
        return "INTERNATIONAL";
    }
}
