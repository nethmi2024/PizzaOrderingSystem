package com.pizza.promotions;

public class FreeExtraCheesePromotion implements Promotion {
    @Override
    public String getDescription() {
        return "Free extra cheese with every pizza!";
    }

    @Override
    public double applyPromotion(double originalCost) {
        return originalCost - 1.00;
    }
}
