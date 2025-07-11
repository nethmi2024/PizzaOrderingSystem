package com.pizza.decorator;

public class BasicPizza implements PizzaOrder {
    public String getDescription() { return "Basic Pizza"; }
    public double getCost() { return 5.00; }
}
