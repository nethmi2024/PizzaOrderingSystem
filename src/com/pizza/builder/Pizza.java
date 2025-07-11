package com.pizza.builder;

import java.util.*;

public class Pizza {
    String crust;
    String sauce;
    String cheese;
    List<String> toppings = new ArrayList<>();
    String name;

    private Pizza() {}

    public static class Builder {
        private Pizza pizza = new Pizza();

        public Builder setCrust(String crust) {
            pizza.crust = crust;
            return this;
        }
        public Builder setSauce(String sauce) {
            pizza.sauce = sauce;
            return this;
        }
        public Builder setCheese(String cheese) {
            pizza.cheese = cheese;
            return this;
        }
        public Builder addTopping(String topping) {
            pizza.toppings.add(topping);
            return this;
        }
        public Builder setName(String name) {
            pizza.name = name;
            return this;
        }
        public Pizza build() {
            return pizza;
        }
    }

    public String toString() {
        return name + " Pizza [Crust: " + crust + ", Sauce: " + sauce + ", Cheese: " + cheese + ", Toppings: " + toppings + "]";
    }
}