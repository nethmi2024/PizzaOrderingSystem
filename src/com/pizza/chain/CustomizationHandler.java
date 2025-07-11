package com.pizza.chain;

public abstract class CustomizationHandler {
    protected CustomizationHandler next;
    public void setNext(CustomizationHandler next) { this.next = next; }
    public abstract void handle(String request);
}