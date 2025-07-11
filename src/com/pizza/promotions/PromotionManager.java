package com.pizza.promotions;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.Month;

public class PromotionManager {
    private List<Promotion> activePromotions = new ArrayList<>();

    public void addPromotion(Promotion promo) {
        activePromotions.add(promo);
    }

    public void clearPromotions() {
        activePromotions.clear();
    }

    public List<Promotion> getActivePromotions() {
        return activePromotions;
    }

    public double applyPromotions(double cost) {
        for (Promotion promo : activePromotions) {
            cost = promo.applyPromotion(cost);
        }
        return cost;
    }
    
    public void loadSeasonalPromotions() {
        LocalDate today = LocalDate.now();
        if (today.getMonth() == Month.DECEMBER) {
            addPromotion(new PercentageDiscountPromotion(0.15)); 
        } else if (today.getMonth() == Month.OCTOBER) {
            addPromotion(new FreeExtraCheesePromotion()); 
        }
    }
    
    public void displayPromotions() {
        if (activePromotions.isEmpty()) {
            System.out.println("No promotions available at the moment.");
        } else {
            System.out.println("Current Promotions:");
            for (Promotion promo : activePromotions) {
                System.out.println("- " + promo.getDescription());
            }
        }
    }
}
