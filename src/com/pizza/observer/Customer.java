package com.pizza.observer;

public class Customer implements Observer {
    private String name;

    public Customer(String name) { this.name = name; }
    public void update(String status) {
        System.out.println(name + " notified: " + status);
    }
}