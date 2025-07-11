package com.pizza.delivery;

public class HomeDelivery implements DeliveryService {
    @Override
    public void deliver(String address) {
        System.out.println("Delivery selected. Delivering to: " + address);
        System.out.println("Estimated delivery time: 30 minutes");
        System.out.println("Thank you for your order! Enjoy your pizza!");
    }
}
