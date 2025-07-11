package com.pizza.user;

import java.util.*;

public class UserManager {
    private static Map<String, User> users = new HashMap<>();

    public static User register(String username, String password) {
        if (users.containsKey(username)) {
            System.out.println("Username already exists.");
            return null;
        }
        User user = new User(username, password);
        users.put(username, user);
        System.out.println("Registration successful.");
        return user;
    }

    public static User login(String username, String password) {
        if (!users.containsKey(username)) {
            System.out.println("User not found.");
            return null;
        }
        User user = users.get(username);
        if (!user.checkPassword(password)) {
            System.out.println("Incorrect password.");
            return null;
        }
        System.out.println("Login successful.");
        return user;
    }
}
