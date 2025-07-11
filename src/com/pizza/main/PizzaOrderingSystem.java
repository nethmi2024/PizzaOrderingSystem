package com.pizza.main;

import com.pizza.builder.Pizza;
import com.pizza.decorator.*;
import com.pizza.strategy.*;
import com.pizza.observer.*;
import com.pizza.promotions.*;
import com.pizza.state.*;
import com.pizza.command.*;
import com.pizza.chain.*;
import com.pizza.delivery.*;
import com.pizza.user.*;

import java.util.Scanner;

public class PizzaOrderingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        outerLoop:
        while (true) {
            System.out.println("Welcome! Are you a new user? (yes/no):");
            String isNew = sc.nextLine();
            User user = null;

            if (isNew.equalsIgnoreCase("yes")) {
                System.out.println("Enter a username:");
                String username = sc.nextLine();
                System.out.println("Enter a password:");
                String password = sc.nextLine();
                user = UserManager.register(username, password);
            } else {
                System.out.println("Enter your username:");
                String username = sc.nextLine();
                System.out.println("Enter your password:");
                String password = sc.nextLine();
                user = UserManager.login(username, password);
            }

            if (user == null) {
                System.out.println("Exiting due to failed authentication.");
                break;  // exit program if login/register fails
            }

            System.out.println("Would you like to view your favorite pizzas? (yes/no):");
            if (sc.nextLine().equalsIgnoreCase("yes")) {
                user.showFavorites();
                System.out.println("Would you like to reorder one? (yes/no):");
                if (sc.nextLine().equalsIgnoreCase("yes")) {
                    System.out.println("Enter the number of the favorite pizza:");
                    int index = Integer.parseInt(sc.nextLine());
                    if (index >= 1 && index <= user.getFavoritePizzas().size()) {
                        Pizza favPizza = user.getFavoritePizzas().get(index - 1);
                        System.out.println("Reordering: " + favPizza);
                        // Execute order
                        CommandExecutor executor = new CommandExecutor();
                        executor.executeCommand(new PlaceOrderCommand(favPizza));

                        if (!askOrderAgain(sc)) break outerLoop;
                        else continue outerLoop;
                    }
                }
            }

            System.out.println("Enter your pizza name:");
            String name = sc.nextLine();

            System.out.println("Choose crust (Thin/Thick/Stuffed):");
            String crust = sc.nextLine();

            System.out.println("Choose sauce (Tomato/Barbecue/Pesto):");
            String sauce = sc.nextLine();

            System.out.println("Choose cheese (Mozzarella/Cheddar/Vegan):");
            String cheese = sc.nextLine();

            Pizza.Builder builder = new Pizza.Builder()
                    .setCrust(crust)
                    .setSauce(sauce)
                    .setCheese(cheese)
                    .setName(name);

            System.out.println("Enter toppings (type 'done' when finished):");
            while (true) {
                String topping = sc.nextLine();
                if (topping.equalsIgnoreCase("done")) break;
                builder.addTopping(topping);
            }

            Pizza myPizza = builder.build();

            System.out.println("Do you want to save this pizza as a favorite? (yes/no):");
            if (sc.nextLine().equalsIgnoreCase("yes")) {
                user.addFavoritePizza(myPizza);
                System.out.println("Pizza saved to favorites.");
            }

            PizzaOrder order = new BasicPizza();
            System.out.println("Would you like extra cheese? (yes/no):");
            if (sc.nextLine().equalsIgnoreCase("yes")) {
                order = new ExtraCheese(order);
            }

            System.out.println("Would you like extra toppings? (yes/no):");
            if (sc.nextLine().equalsIgnoreCase("yes")) {
                order = new ExtraToppings(order);
            }

            double cost = order.getCost();

            PromotionManager promoManager = new PromotionManager();
            promoManager.addPromotion(new PercentageDiscountPromotion(0.10));
            promoManager.addPromotion(new FreeExtraCheesePromotion());

            promoManager.displayPromotions();

            System.out.printf("Original total: $%.2f%n", cost);
            cost = promoManager.applyPromotions(cost);
            System.out.printf("Total after promotions: $%.2f%n", cost);

            System.out.println("You have " + user.getLoyaltyPoints() + " loyalty points.");
            System.out.println("Would you like to redeem 10 points for a $1 discount? (yes/no):");

            if (sc.nextLine().equalsIgnoreCase("yes")) {
                int pointsToUse = (int) Math.min(user.getLoyaltyPoints() / 10 * 10, cost * 10);
                double discount = pointsToUse / 10.0;
                boolean success = user.redeemPoints(pointsToUse);
                if (success) {
                    cost -= discount;
                    System.out.println("Redeemed " + pointsToUse + " points. Discount applied: $" + discount);
                } else {
                    System.out.println("Not enough points to redeem.");
                }
            }

            System.out.printf("Final amount after discount: $%.2f%n", cost);
            System.out.println("Choose payment method (credit/digital):");
            PaymentStrategy payment = sc.nextLine().equalsIgnoreCase("credit") ?
                    new CreditCardPayment() : new DigitalWalletPayment();
            payment.pay(cost);

            int earnedPoints = (int) cost;
            user.addPoints(earnedPoints);
            System.out.println("You earned " + earnedPoints + " loyalty points.");
            System.out.println("Total loyalty points: " + user.getLoyaltyPoints());

            System.out.println("Enter your name for notifications:");
            String customerName = sc.nextLine();
            OrderStatus status = new OrderStatus();
            Customer customer = new Customer(customerName);
            status.addObserver(customer);

            OrderContext ctx = new OrderContext();
            System.out.println("Initial Order State: " + ctx.getStatus());
            ctx.nextState();
            status.setStatus(ctx.getStatus());
            ctx.nextState();
            status.setStatus(ctx.getStatus());

            CommandExecutor executor = new CommandExecutor();
            executor.executeCommand(new PlaceOrderCommand(myPizza));

            System.out.println("Would you like to leave feedback? (yes/no):");
            if (sc.nextLine().equalsIgnoreCase("yes")) {
                System.out.println("Enter your feedback:");
                executor.executeCommand(new ProvideFeedbackCommand(sc.nextLine()));
            }

            CustomizationHandler crustHandler = new CrustHandler();
            CustomizationHandler sauceHandler = new SauceHandler();
            CustomizationHandler toppingHandler = new ToppingHandler();
            crustHandler.setNext(sauceHandler);
            sauceHandler.setNext(toppingHandler);
            crustHandler.handle("crust");
            crustHandler.handle("sauce");
            crustHandler.handle("topping");

            System.out.println("Select delivery type (pickup/delivery):");
            String deliveryType = sc.nextLine();
            DeliveryService deliveryService;
            if (deliveryType.equalsIgnoreCase("delivery")) {
                System.out.println("Enter your address:");
                String address = sc.nextLine();
                deliveryService = new HomeDelivery();
                deliveryService.deliver(address);
            } else {
                deliveryService = new PickupDelivery();
                deliveryService.deliver(null);
            }

            if (!askOrderAgain(sc)) {
                break outerLoop;
            }
        }

        System.out.println("Thank you for using the Pizza Ordering System. Goodbye!");
        sc.close();
    }

    private static boolean askOrderAgain(Scanner sc) {
        System.out.println("Would you like to place another order? (yes/no):");
        String answer = sc.nextLine();
        return answer.equalsIgnoreCase("yes");
    }
}
