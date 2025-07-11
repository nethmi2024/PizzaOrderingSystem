package com.pizza.decorator;

public class ExtraToppings extends PizzaDecorator {
    public ExtraToppings(PizzaOrder pizza) { super(pizza); }
    public String getDescription() { return tempPizza.getDescription() + ", Extra Toppings"; }
    public double getCost() { return tempPizza.getCost() + 1.50; }
}