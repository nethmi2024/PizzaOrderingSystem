package com.pizza.decorator;

abstract class PizzaDecorator implements PizzaOrder {
    protected PizzaOrder tempPizza;
    public PizzaDecorator(PizzaOrder pizza) { this.tempPizza = pizza; }
}