package com.techelevator.domain;

import java.text.NumberFormat;

public abstract class VendingItem {
    private final String itemName;
    private final double price;
    private static final String DISPENSE_MESSAGE_SUFFIX = ", Yum!";

    public VendingItem(String itemName, double price) {
        this.itemName = itemName;
        this.price = price;
    }

    public abstract String getDispenseMessage();

    protected String dispenseVendingItemMessage(String prefix) {
        return prefix + DISPENSE_MESSAGE_SUFFIX;
    }

    public String getItemName() {
        return itemName;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return itemName + " " + NumberFormat.getCurrencyInstance().format(price);
    }
}
