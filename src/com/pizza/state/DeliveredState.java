package com.pizza.state;

class DeliveredState implements OrderState {
    public void next(OrderContext ctx) { System.out.println("Order already delivered."); }
    public String getStatus() { return "Delivered"; }
}

