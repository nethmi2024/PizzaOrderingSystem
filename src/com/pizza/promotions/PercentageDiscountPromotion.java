package com.pizza.promotions;

public class PercentageDiscountPromotion implements Promotion {
    private double percentage;

    public PercentageDiscountPromotion(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public String getDescription() {
        return (int)(percentage * 100) + "% off your order!";
    }

    @Override
    public double applyPromotion(double originalCost) {
        return originalCost * (1 - percentage);
    }
}
