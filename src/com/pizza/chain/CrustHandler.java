package com.pizza.chain;

public class CrustHandler extends CustomizationHandler {
    public void handle(String request) {
        if (request.equals("crust"))
            System.out.println("Crust selected.");
        else if (next != null)
            next.handle(request);
    }
}