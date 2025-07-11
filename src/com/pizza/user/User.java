package com.pizza.user;

import com.pizza.builder.Pizza;
import java.util.*;

public class User {
    private String username;
    private String password;
    private List<Pizza> favoritePizzas = new ArrayList<>();
    private int loyaltyPoints = 0;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public void addFavoritePizza(Pizza pizza) {
        favoritePizzas.add(pizza);
    }

    public List<Pizza> getFavoritePizzas() {
        return favoritePizzas;
    }

    public void showFavorites() {
        if (favoritePizzas.isEmpty()) {
            System.out.println("No favorite pizzas.");
        } else {
            for (int i = 0; i < favoritePizzas.size(); i++) {
                System.out.println((i + 1) + ". " + favoritePizzas.get(i));
            }
        }
    }

    //Loyalty methods
    public void addPoints(int points) {
        loyaltyPoints += points;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public boolean redeemPoints(int points) {
        if (points <= loyaltyPoints) {
            loyaltyPoints -= points;
            return true;
        }
        return false;
    }
}
