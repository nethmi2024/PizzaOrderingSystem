package com.pizza.state;

interface OrderState {
    void next(OrderContext ctx);
    String getStatus();
}
