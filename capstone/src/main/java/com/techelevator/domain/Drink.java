package com.techelevator.domain;

public class Drink extends VendingItem {
    private static final String DISPENSE_MESSAGE_PREFIX = "Glug Glug";

    public Drink(String itemName, double price) {
        super(itemName, price);
    }

    @Override
    public String getDispenseMessage() {
        return dispenseVendingItemMessage(DISPENSE_MESSAGE_PREFIX);
    }
}
