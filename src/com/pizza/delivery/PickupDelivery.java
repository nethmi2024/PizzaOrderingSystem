package com.pizza.delivery;

public class PickupDelivery implements DeliveryService {
    @Override
    public void deliver(String address) {
        System.out.println("Pickup selected. Please collect your order at the store.");
        System.out.println("Thank you for your order! Enjoy your pizza!");
    }
}
