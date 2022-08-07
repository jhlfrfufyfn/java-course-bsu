package com.bsu;

public class Food extends Item {
    private int quantity;

    public Food(int weight, int quantity) {
        super(weight);
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
