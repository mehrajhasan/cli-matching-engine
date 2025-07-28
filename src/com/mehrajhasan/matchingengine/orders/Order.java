package com.mehrajhasan.matchingengine.orders;

public class Order {
    public enum Side {
        BUY,
        SELL
    }

    private static long nextOrderId = 1;

    private final long id;
    private final Side side;
    private final double price;
    private int quantity;

    public Order(Side side, double price, int quantity) {
        this.id = nextOrderId++;
        this.side = side;
        this.price = price;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public Side getSide() {
        return side;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void reduceQuantity(int amount) {
        if (amount <= quantity) {
            this.quantity -= amount;
        }
    }

    @Override
    public String toString() {
        return String.format("Order{id=%d, side=%s, price=%.2f, quantity=%d}",
                id, side, price, quantity);
    }
}