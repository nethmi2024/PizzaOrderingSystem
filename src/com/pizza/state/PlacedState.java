package com.pizza.state;

class PlacedState implements OrderState {
    public void next(OrderContext ctx) { ctx.setState(new InPreparationState()); }
    public String getStatus() { return "Order Placed"; }
}