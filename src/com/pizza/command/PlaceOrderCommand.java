package com.pizza.command;

import com.pizza.builder.Pizza;

public class PlaceOrderCommand implements Command {
    private Pizza pizza;
    public PlaceOrderCommand(Pizza pizza) { this.pizza = pizza; }
    public void execute() {
        System.out.println("Order placed: " + pizza);
    }
}