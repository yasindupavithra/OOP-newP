package com.course.system.model.strategy;

/**
 * IMPROVEMENT (Design Pattern): Concrete Strategy for local students.
 * Local students pay the base fee with no multiplier.
 */
public class LocalFeeStrategy implements FeeStrategy {
    @Override
    public double calculateFee(double baseFee) {
        return baseFee;
    }

    @Override
    public String getStrategyName() {
        return "REGULAR";
    }
}
