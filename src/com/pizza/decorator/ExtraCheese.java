package com.pizza.decorator;

public class ExtraCheese extends PizzaDecorator {
    public ExtraCheese(PizzaOrder pizza) { super(pizza); }
    public String getDescription() { return tempPizza.getDescription() + ", Extra Cheese"; }
    public double getCost() { return tempPizza.getCost() + 1.00; }
}