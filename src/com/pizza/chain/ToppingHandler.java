package com.pizza.chain;

public class ToppingHandler extends CustomizationHandler {
    public void handle(String request) {
        if (request.equals("topping"))
            System.out.println("Topping selected.");
        else if (next != null)
            next.handle(request);
    }
}
