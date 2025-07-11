package com.pizza.strategy;

public class DigitalWalletPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using Digital Wallet.");
    }
}