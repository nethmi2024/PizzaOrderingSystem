package com.pizza.state;

class OutForDeliveryState implements OrderState {
    public void next(OrderContext ctx) { ctx.setState(new DeliveredState()); }
    public String getStatus() { return "Out for Delivery"; }
}