package com.pizza.chain;

public class SauceHandler extends CustomizationHandler {
    public void handle(String request) {
        if (request.equals("sauce"))
            System.out.println("Sauce selected.");
        else if (next != null)
            next.handle(request);
    }
}