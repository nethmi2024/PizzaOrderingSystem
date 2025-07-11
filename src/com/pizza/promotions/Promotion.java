package com.pizza.promotions;

public interface Promotion {
    String getDescription();
    double applyPromotion(double originalCost);
}
