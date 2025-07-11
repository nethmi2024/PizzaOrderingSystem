package com.pizza.state;

public class OrderContext {
    private OrderState state;
    public OrderContext() { state = new PlacedState(); }
    public void setState(OrderState state) { this.state = state; }
    public void nextState() { state.next(this); }
    public String getStatus() { return state.getStatus(); }
}