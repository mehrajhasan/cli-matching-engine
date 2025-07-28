package com.mehrajhasan.matchingengine;
import com.mehrajhasan.matchingengine.orders.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MatchingEngine {
    //create the order book as a list of Order type
    private static final List<Order> orderBook = new ArrayList<>();

    public static void main(String[] args) {
        //scanner for input
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== CLI Matching Engine ===");
        //want input structured as `[side] [price] [quantity]`
        System.out.println("Enter orders in the format: buy 101.5 10 or sell 100.0 5");
        System.out.println("Type 'exit' to quit.\n");

        while (true) {
            System.out.print("> ");
            //read input into input string
            String input = scanner.nextLine();

            // TODO: Trim whitespace and check if input is 'exit'
            if(input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting...");
                break;
            }

            String[] inputArray = input.split("\\s+");
            if (inputArray.length != 3) {
                System.out.println("Invalid input. Use the following format: [buy/sell] [price] [quantity]");
                continue;
            }

            String sideval = inputArray[0];
            double price = Double.parseDouble(inputArray[1]);
            int quantity = Integer.parseInt(inputArray[2]);


            Order.Side side;
            if(sideval.equalsIgnoreCase("buy")) {
                side = Order.Side.BUY;
            }
            else if(sideval.equalsIgnoreCase("sell")) {
                side = Order.Side.SELL;
            }
            else {
                System.out.println("Invalid input. Use the following format: [buy/sell] [price] [quantity]");
                continue;
            }

            //once validated, create a new order with info
            Order order = new Order(side, price, quantity);

            //add to orderbook
            orderBook.add(order);

            //display order confirmed
            System.out.println("Order confirmed: " + order);
            printOrderBook();
        }

        scanner.close();
    }

    //func to print current buy side and sell side orders
    private static void printOrderBook() {
        System.out.println("\n--- Current Order Book ---");

        // Group and show BUY orders
        System.out.println("BUY Orders:");
        for (Order order : orderBook) {
            if (order.getSide() == Order.Side.BUY) {
                System.out.println("\u001B[32m" + order.getSide() + " " + order.getPrice() + " " + order.getQuantity() + "\u001B[0m");
            }
        }

        System.out.println();

        // Group and show SELL orders
        System.out.println("SELL Orders:");
        for (Order order : orderBook) {
            if (order.getSide() == Order.Side.SELL) {
                System.out.println("\u001B[31m"+ order.getSide() + " " + order.getPrice() + " " + order.getQuantity() + "\u001B[0m");
            }
        }

        System.out.println("--------------------------\n");
    }

}
