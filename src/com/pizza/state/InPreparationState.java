package com.pizza.state;

class InPreparationState implements OrderState {
    public void next(OrderContext ctx) { ctx.setState(new OutForDeliveryState()); }
    public String getStatus() { return "In Preparation"; }
}
